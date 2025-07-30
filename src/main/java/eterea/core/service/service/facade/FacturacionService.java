package eterea.core.service.service.facade;

import eterea.core.service.client.afip.FacturacionAfipClient;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.PosicionIva;
import eterea.core.service.model.ReservaContext;
import eterea.core.service.model.Track;
import eterea.core.service.model.client.pyafipws.FacturaResponseDto;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private final ComprobanteService comprobanteService;
    private final ValorMovimientoService valorMovimientoService;
    private final CuentaMovimientoService cuentaMovimientoService;
    private final FacturacionAfipClient facturacionAfipClient;
    private final FacturacionElectronicaService facturacionElectronicaService;
    private final RegistroCaeService registroCaeService;

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
                              RegistraFacturaService registraFacturaService, ComprobanteService comprobanteService, ValorMovimientoService valorMovimientoService, CuentaMovimientoService cuentaMovimientoService, FacturacionAfipClient facturacionAfipClient, FacturacionElectronicaService facturacionElectronicaService, RegistroCaeService registroCaeService) {
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
        this.comprobanteService = comprobanteService;
        this.valorMovimientoService = valorMovimientoService;
        this.cuentaMovimientoService = cuentaMovimientoService;
        this.facturacionAfipClient = facturacionAfipClient;
        this.facturacionElectronicaService = facturacionElectronicaService;
        this.registroCaeService = registroCaeService;
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

        if (valorId == 0) {
            log.info("\n\n\nSin VALORES para REGISTRAR\n\n\n");
            return null;
        }

        Valor valor = valorService.findByValorId(valorId);
        log.debug("Valor -> {}", valor.jsonify());

        if (track == null) {
            track = trackService.startTracking("transaccion-factura-programa-dia");
        }

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

    public ClienteMovimiento registroNotaCreditoFromFactura(Long clienteMovimientoId, Integer comprobanteId) {
        var track = trackService.startTracking("registro-nota-credito-from-factura");
        log.debug("Processing FacturacionService.registroNotaCreditoFromFactura");
        var clienteMovimiento = clienteMovimientoService.findByClienteMovimientoId(clienteMovimientoId);
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());
        var comprobanteNC = comprobanteService.findByComprobanteId(comprobanteId);
        log.debug("ComprobanteNC -> {}", comprobanteNC.jsonify());
        log.debug("Loading valores . . .");
        var valorMovimientos = valorMovimientoService.findAllByContable(clienteMovimiento.getFechaContable(), clienteMovimiento.getOrdenContable());
        log.debug("Loading artículos . . .");
        var articuloMovimientos = articuloMovimientoService.findAllByClienteMovimientoId(clienteMovimientoId);
        log.debug("Loading imputaciones . . . ");
        var cuentaMovimientos = cuentaMovimientoService.findAllByContable(clienteMovimiento.getFechaContable(), clienteMovimiento.getOrdenContable());
        log.debug("Loading Factura ARCA . . .");
        var facturaArca = facturacionAfipClient.consultaComprobante(Objects.requireNonNull(clienteMovimiento.getComprobante()).getComprobanteAfipId(), clienteMovimiento.getPuntoVenta(), clienteMovimiento.getNumeroComprobante());
        if (facturaArca.getFactura() == null) {
            log.debug("\n\n\nSin DATOS en ARCA\n\n\n");
            return null;
        }
        PosicionIva posicionIva = Objects.requireNonNull(clienteMovimiento.getCliente()).getPosicion();
        var idPosicionIvaArca = 5;
        if (posicionIva != null) {
            idPosicionIvaArca = posicionIva.getIdPosicionIvaArca();
        }

        // Excepción propia de TSA
        if (idPosicionIvaArca == 6) {
            idPosicionIvaArca = 5;
        }

        var factura = facturaArca.getFactura();
        log.debug("FacturaDto -> {}", factura.jsonify());
        var facturacionDto = FacturacionDto.builder()
                .tipoDocumento(factura.getTipoDoc())
                .documento(String.valueOf(factura.getNroDoc()))
                .tipoAfip(comprobanteNC.getComprobanteAfipId())
                .puntoVenta(comprobanteNC.getPuntoVenta())
                .total(factura.getImpTotal().setScale(2, RoundingMode.HALF_UP))
                .exento(factura.getImpOpEx().setScale(2, RoundingMode.HALF_UP))
                .neto(factura.getImpNeto().setScale(2, RoundingMode.HALF_UP))
                .neto105(BigDecimal.ZERO)
                .iva(factura.getImpIva().setScale(2, RoundingMode.HALF_UP))
                .iva105(BigDecimal.ZERO)
                .idCondicionIva(idPosicionIvaArca)
                .asociadoFechaComprobante(LocalDate.parse(factura.getFechaCbte().toString()).format(DateTimeFormatter.ofPattern("yyyyMMdd")))
                .asociadoTipoAfip(Objects.requireNonNull(clienteMovimiento.getComprobante().getComprobanteAfip()).getComprobanteAfipId())
                .asociadoPuntoVenta(clienteMovimiento.getPuntoVenta())
                .asociadoNumeroComprobante(clienteMovimiento.getNumeroComprobante())
                .build();
        log.debug("FacturacionDto -> {}", facturacionDto.jsonify());

        try {
            facturacionDto = facturacionElectronicaService.makeFactura(facturacionDto);
            log.debug("After makeFactura -> {}", facturacionDto.jsonify());
        } catch (WebClientResponseException e) {
            log.debug("Servicio de Facturación NO disponible");
            return null;
        }

        if (!facturacionDto.getResultado().equals("A")) {
            log.debug("Facturación NO Aprobada por ARCA");
            return null;
        }

        // Convierte fechas
        SimpleDateFormat formatoInDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatoOutDate = new SimpleDateFormat("ddMMyyyy");
        Date vencimientoCae = null;
        try {
            vencimientoCae = formatoInDate.parse(facturacionDto.getVencimientoCae());
        } catch (ParseException e) {
            log.error("Error al parsear vencimientoCae", e);
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");

        String fechaComprobanteString;
        try {
            Date fechaComprobante = formatoInDate.parse(facturacionDto.getFechaComprobante());
            fechaComprobanteString = formatoOutDate.format(fechaComprobante);
        } catch (ParseException | NullPointerException e) {
            fechaComprobanteString = ToolService.dateAbsoluteArgentina().format(dateTimeFormatter);
            log.warn("No se pudo parsear fechaComprobante de facturacionDto. Usando fecha actual. " + e.getMessage());
        }

        // Registra el resultado de la AFIP
        RegistroCae registroCae = new RegistroCae.Builder()
                .comprobanteId(comprobanteNC.getComprobanteId())
                .puntoVenta(facturacionDto.getPuntoVenta())
                .numeroComprobante(facturacionDto.getNumeroComprobante())
                .clienteId(clienteMovimiento.getClienteId())
                .cuit("")
                .total(facturacionDto.getTotal())
                .exento(facturacionDto.getExento())
                .neto(facturacionDto.getNeto())
                .neto105(facturacionDto.getNeto105())
                .iva(facturacionDto.getIva())
                .iva105(facturacionDto.getIva105())
                .cae(facturacionDto.getCae())
                .fecha(fechaComprobanteString)
                .caeVencimiento(formatoOutDate.format(vencimientoCae))
                .tipoDocumento(facturacionDto.getTipoDocumento())
                .numeroDocumento(new BigDecimal(facturacionDto.getDocumento()))
                .clienteMovimientoIdAsociado(clienteMovimiento.getClienteMovimientoId())
                .trackUuid(track.getUuid())
                .build();
        registroCae = registroCaeService.add(registroCae);
        log.debug("RegistroCae -> {}", registroCae.jsonify());

        var notaCredito = new ClienteMovimiento.Builder()
                .negocioId(clienteMovimiento.getNegocioId())
                .empresaId(clienteMovimiento.getEmpresaId())
                .clienteId(clienteMovimiento.getClienteId())
                .comprobanteId(comprobanteNC.getComprobanteId())
                .fechaComprobante(ToolService.dateAbsoluteArgentina())
                .fechaVencimiento(ToolService.dateAbsoluteArgentina())
                .importe(facturacionDto.getTotal().multiply(new BigDecimal(-1)))
                .cancelado(facturacionDto.getTotal().multiply(new BigDecimal(-1)))  // contado
                .puntoVenta(comprobanteNC.getPuntoVenta())
                .numeroComprobante(facturacionDto.getNumeroComprobante())
                .montoIva(facturacionDto.getIva().multiply(new BigDecimal(-1)))
                .montoIvaRni(facturacionDto.getIva105().multiply(new BigDecimal(-1)))
                .neto(facturacionDto.getNeto().multiply(new BigDecimal(-1)))
                .letraComprobante(comprobanteNC.getLetraComprobante())
                .montoExento(facturacionDto.getExento().multiply(new BigDecimal(-1)))
                .reservaId(clienteMovimiento.getReservaId())
                .cae(facturacionDto.getCae())
                .caeVencimiento(facturacionDto.getVencimientoCae())
                .monedaId(1)
                .cotizacion(BigDecimal.ONE)
                .letras(ToolService.number_2_text(facturacionDto.getTotal()))
                .observaciones("NC generada automáticamente")
                .trackUuid(track.getUuid())
                .build();
        log.debug("NotaCredito -> {}", notaCredito.jsonify());

        List<ValorMovimiento> valorMovimientosNC = new ArrayList<>();
        for (var valorMovimiento : valorMovimientos) {
            log.debug("ValorMovimiento -> {}", valorMovimiento.jsonify());
            valorMovimientosNC.add(new ValorMovimiento.Builder()
                    .negocioId(valorMovimiento.getNegocioId())
                    .clienteId(valorMovimiento.getClienteId())
                    .proveedorId(0L)
                    .comprobanteId(comprobanteNC.getComprobanteId())
                    .fechaEmision(clienteMovimiento.getFechaComprobante())
                    .fechaVencimiento(clienteMovimiento.getFechaComprobante())
                    .valorId(valorMovimiento.getValorId())
                    .numeroComprobante(0L)
                    .importe(valorMovimiento.getImporte().multiply(new BigDecimal(-1)))
                    .numeroCuenta(valorMovimiento.getNumeroCuenta())
                    .proveedorMovimientoId(0L)
                    .titular("")
                    .banco("")
                    .receptor("")
                    .estadoId(0)
                    .cierreCajaId(0L)
                    .observaciones(valorMovimiento.getObservaciones())
                    .trackUuid(track.getUuid())
                    .build()
            );
            log.debug("ValorMovimientoNC -> {}", valorMovimientosNC.getLast().jsonify());
        }

        List<ArticuloMovimiento> articuloMovimientosNC = new ArrayList<>();
        for (var articuloMovimiento : articuloMovimientos) {
            log.debug("ArticuloMovimiento -> {}", articuloMovimiento.jsonify());
            articuloMovimientosNC.add(new ArticuloMovimiento.Builder()
                    .centroStockId(articuloMovimiento.getCentroStockId())
                    .comprobanteId(comprobanteNC.getComprobanteId())
                    .item(articuloMovimiento.getItem())
                    .articuloId(articuloMovimiento.getArticuloId())
                    .negocioId(articuloMovimiento.getNegocioId())
                    .cantidad(Objects.requireNonNull(articuloMovimiento.getCantidad()).multiply(new BigDecimal(-1)))
                    .precioUnitario(articuloMovimiento.getPrecioUnitario())
                    .precioUnitarioSinIva(articuloMovimiento.getPrecioUnitarioSinIva())
                    .precioUnitarioConIva(articuloMovimiento.getPrecioUnitarioConIva())
                    .numeroCuenta(articuloMovimiento.getNumeroCuenta())
                    .iva105(articuloMovimiento.getIva105())
                    .exento(articuloMovimiento.getExento())
                    .fechaMovimiento(articuloMovimiento.getFechaMovimiento())
                    .fechaFactura(articuloMovimiento.getFechaFactura())
                    .precioCompra(articuloMovimiento.getPrecioCompra())
                    .trackUuid(track.getUuid())
                    .build());
            log.debug("ArticuloMovimientoNC -> {}", articuloMovimientosNC.getLast().jsonify());
        }

        // Comienza registro en la db
        int ordenContable = cuentaMovimientoService.nextOrdenContable(notaCredito.getFechaComprobante());
        String concepto = String.format("Nro: %04d %06d", notaCredito.getPuntoVenta(), notaCredito.getNumeroComprobante());
        List<CuentaMovimiento> cuentaMovimientosNC = new ArrayList<>();
        for (var cuentaMovimiento : cuentaMovimientos) {
            cuentaMovimientosNC.add(new CuentaMovimiento.Builder()
                    .negocioId(cuentaMovimiento.getNegocioId())
                    .numeroCuenta(cuentaMovimiento.getNumeroCuenta())
                    .debita((byte) (1 - cuentaMovimiento.getDebita()))
                    .importe(cuentaMovimiento.getImporte())
                    .item(cuentaMovimiento.getItem())
                    .fecha(notaCredito.getFechaComprobante())
                    .comprobanteId(notaCredito.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(cuentaMovimiento.getClienteId())
                    .subrubroId(cuentaMovimiento.getSubrubroId())
                    .concepto(concepto)
                    .build());
            log.debug("CuentaMovimiento -> {}", cuentaMovimientosNC.getLast().jsonify());
        }
        cuentaMovimientoService.saveAll(cuentaMovimientosNC);

        // Registra clienteMovimiento
        notaCredito.setFechaContable(notaCredito.getFechaComprobante());
        notaCredito.setOrdenContable(ordenContable);
        notaCredito = clienteMovimientoService.add(notaCredito);
        log.debug("NotaCredito -> {}", notaCredito.jsonify());

        // Registra valorMovimiento
        for (var valorMovimiento : valorMovimientosNC) {
            valorMovimiento.setClienteMovimientoId(notaCredito.getClienteMovimientoId());
            valorMovimiento.setFechaContable(notaCredito.getFechaContable());
            valorMovimiento.setOrdenContable(notaCredito.getOrdenContable());
            valorMovimiento = valorMovimientoService.add(valorMovimiento);
            log.debug("ValorMovimiento -> {}", valorMovimiento.jsonify());
        }

        // Registra articuloMovimientos
        for (var articuloMovimiento : articuloMovimientosNC) {
            articuloMovimiento.setClienteMovimientoId(notaCredito.getClienteMovimientoId());
            log.debug("ArticuloMovimiento para registrar -> {}", articuloMovimiento.jsonify());
        }
        articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

        return notaCredito;
    }

}

