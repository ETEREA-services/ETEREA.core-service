package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.ReservaContext;
import eterea.core.service.model.Track;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Service
@Slf4j
public class FacturacionService {

    private final VoucherService voucherService;
    private final OrderNoteService orderNoteService;
    private final ValorService valorService;
    private final ClienteMovimientoService clienteMovimientoService;
    private final ReservaContextService reservaContextService;
    private final ReservaArticuloService reservaArticuloService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final ReservaService reservaService;
    private final ContabilidadService contabilidadService;
    private final TrackService trackService;
    private final RegistraFacturaService registraFacturaService;

    public FacturacionService(VoucherService voucherService,
                              OrderNoteService orderNoteService,
                              ValorService valorService,
                              ClienteMovimientoService clienteMovimientoService,
                              ReservaContextService reservaContextService,
                              ReservaArticuloService reservaArticuloService,
                              ArticuloMovimientoService articuloMovimientoService,
                              ReservaService reservaService,
                              ContabilidadService contabilidadService,
                              TrackService trackService,
                              RegistraFacturaService registraFacturaService) {
        this.voucherService = voucherService;
        this.orderNoteService = orderNoteService;
        this.valorService = valorService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.reservaContextService = reservaContextService;
        this.reservaArticuloService = reservaArticuloService;
        this.articuloMovimientoService = articuloMovimientoService;
        this.reservaService = reservaService;
        this.contabilidadService = contabilidadService;
        this.trackService = trackService;
        this.registraFacturaService = registraFacturaService;
    }

    public ClienteMovimiento registraTransaccionFacturaProgramaDia(Reserva reserva,
                                                                   FacturacionDto facturacionDto,
                                                                   Comprobante comprobante,
                                                                   Empresa empresa,
                                                                   Cliente cliente,
                                                                   Parametro parametro,
                                                                   ReservaContext reservaContext,
                                                                   Track track,
                                                                   Boolean soloFactura) {
        log.debug("Processing FacturacionService.registraTransaccionFacturaProgramaDia");
        if (track == null) {
            track = trackService.startTracking("transaccion-factura-programa-dia");
        }
        Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
        log.debug("Voucher -> {}", voucher.jsonify());
        OrderNote orderNote = orderNoteService.findByOrderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())));
        log.debug("OrderNote -> {}", orderNote.jsonify());

        // Mapea las formas de pago
        int valorId = 0;
        var payment = orderNote.getPayment();
        assert payment != null;
        if (Objects.equals(payment.getMedioPago(), "0")) {
            // Pago QR
            valorId = 72;
        } else {
            valorId = switch (payment.getMarcaTarjeta()) {
                case "American Express" -> 64;
                case "Cabal" -> 67;
                case "Cabal Du00e9bito" -> 66;
                case "Maestro" -> 61;
                case "MasterCard" -> 62;
                case "MasterCard Debito" -> 61;
                case "Tarjeta Naranja" -> 60;
                case "Visa Cru00e9dito" -> 60;
                case "Visa Debito" -> 59;
                case null -> 0;
                default -> 0;
            };
        };
        Valor valor = valorService.findByValorId(valorId);
        log.debug("Valor -> {}", valor.jsonify());

        String observaciones = "Pedido web #" + orderNote.getOrderNumberId() + " - Reserva #" + reserva.getReservaId();

        // Convierte fecha de comprobante a UTC
        OffsetDateTime fechaComprobante;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(facturacionDto.getFechaComprobante(), formatter);
            // Se convierte a UTC
            ZoneId utcZone = ZoneId.of("UTC");
            fechaComprobante = localDate.atStartOfDay(utcZone).toOffsetDateTime();
        } catch (Exception e) {
            log.warn("No se pudo parsear fechaComprobante de facturacionDto. Usando fecha actual en UTC. " + e.getMessage());
            fechaComprobante = ToolService.dateAbsoluteArgentina();
        }

        var reservaArticulos = reservaArticuloService.findAllByReservaId(reserva.getReservaId());
        log.debug("Reserva Articulos cargados");

        var clienteMovimiento = registraFacturaService.registraFacturaCompleta(
                empresa,
                cliente,
                comprobante,
                fechaComprobante,
                facturacionDto,
                reserva,
                observaciones,
                track,
                valor,
                reservaArticulos,
                parametro
        );
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());

        if (soloFactura == true) {
            return clienteMovimiento;
        }

        // Registra reservaContext
        reservaContext.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
        log.debug("ReservaContext -> {}", reservaContext.jsonify());

        reserva.setFacturada((byte) 1);
        reserva.setVerificada((byte) 1);
        reserva = reservaService.update(reserva, reserva.getReservaId());
        log.debug("Reserva -> {}", reserva.jsonify());

        voucher.setConfirmado((byte) 1);
        voucher = voucherService.update(voucher, voucher.getVoucherId());
        log.debug("Voucher -> {}", voucher.jsonify());

        track = trackService.endTracking(track);
        log.debug("Track -> {}", track.jsonify());

        return clienteMovimiento;

    }

    @Transactional
    public ClienteMovimiento registraTransaccionFacturaFaltante(ClienteMovimiento clienteMovimiento,
                                                                ArticuloMovimiento articuloMovimiento) {
        log.debug("Processing FacturacionService.registraTransaccionFacturaFaltante");
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());
        articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);
        log.debug("ArticuloMovimiento -> {}", articuloMovimiento.jsonify());
        contabilidadService.registraFacturaFaltanteCuentaCorriente(clienteMovimiento, articuloMovimiento);
        return clienteMovimiento;
    }

}

