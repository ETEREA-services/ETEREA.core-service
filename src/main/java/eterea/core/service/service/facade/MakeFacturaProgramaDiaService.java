package eterea.core.service.service.facade;

import eterea.core.service.client.report.MakeFacturaReportClient;
import eterea.core.service.kotlin.exception.ReservaContextException;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.PosicionIva;
import eterea.core.service.model.ReservaContext;
import eterea.core.service.model.Track;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

@Service
@Slf4j
public class MakeFacturaProgramaDiaService {

    private final ComprobanteService comprobanteService;
    private final ReservaService reservaService;
    private final EmpresaService empresaService;
    private final ReservaArticuloService reservaArticuloService;
    private final ParametroService parametroService;
    private final FacturacionElectronicaService facturacionElectronicaService;
    private final RegistroCaeService registroCaeService;
    private final ClienteService clienteService;
    private final ArticuloService articuloService;
    private final ReservaContextService reservaContextService;
    private final OrderNoteService orderNoteService;
    private final MakeFacturaReportClient makeFacturaReportClient;
    private final Environment environment;
    private final VoucherService voucherService;
    private final FacturacionService facturacionService;
    private final TrackService trackService;
    private final RegistraFacturaService registraFacturaService;

    public MakeFacturaProgramaDiaService(ComprobanteService comprobanteService,
                                         ReservaService reservaService,
                                         EmpresaService empresaService,
                                         ReservaArticuloService reservaArticuloService,
                                         ParametroService parametroService,
                                         FacturacionElectronicaService facturacionElectronicaService,
                                         RegistroCaeService registroCaeService,
                                         ClienteService clienteService,
                                         ArticuloService articuloService,
                                         ReservaContextService reservaContextService,
                                         OrderNoteService orderNoteService,
                                         MakeFacturaReportClient makeFacturaReportClient,
                                         Environment environment,
                                         VoucherService voucherService,
                                         FacturacionService facturacionService, TrackService trackService, RegistraFacturaService registraFacturaService) {
        this.comprobanteService = comprobanteService;
        this.reservaService = reservaService;
        this.empresaService = empresaService;
        this.reservaArticuloService = reservaArticuloService;
        this.parametroService = parametroService;
        this.facturacionElectronicaService = facturacionElectronicaService;
        this.registroCaeService = registroCaeService;
        this.clienteService = clienteService;
        this.articuloService = articuloService;
        this.reservaContextService = reservaContextService;
        this.orderNoteService = orderNoteService;
        this.makeFacturaReportClient = makeFacturaReportClient;
        this.environment = environment;
        this.voucherService = voucherService;
        this.facturacionService = facturacionService;
        this.trackService = trackService;
        this.registraFacturaService = registraFacturaService;
    }

    public boolean facturaReserva(Long reservaId, Integer comprobanteId, Track track) {
        if (track == null) {
            track = trackService.startTracking("factura-reserva");
        }
        Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
        if (comprobante.getFacturaElectronica() == 0) {
            return false;
        }
        log.debug("Comprobante -> {}", comprobante.jsonify());
        Empresa empresa = empresaService.findTop();
        Parametro parametro = parametroService.findTop();
        String moneda = "PES";
        Reserva reserva = reservaService.findByReservaId(reservaId);
        log.debug("Reserva -> {}", reserva.jsonify());
        if (reserva.getFacturada() == (byte) 1) {
            log.debug("reserva facturada={}", reserva.getReservaId());
            try {
                var reservaContext = reservaContextService.findByReservaId(reservaId);
                reservaContext.setFacturadoFuera((byte) 1);
                reservaContext.setFacturaPendiente((byte) 0);
                reservaContext.setEnvioPendiente((byte) 0);
                reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
                log.debug("ReservaContext -> {}", reservaContext.jsonify());
            } catch (ReservaContextException e) {
                log.debug("No hay reserva_context para esta reserva");
            }
            return false;
        }
        Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
        log.debug("Voucher -> {}", voucher.jsonify());
        Cliente cliente = clienteService.findByClienteId(reserva.getClienteId());
        PosicionIva posicionIva = cliente.getPosicion();
        var idPosicionIvaArca = 5;
        if (posicionIva != null) {
            idPosicionIvaArca = posicionIva.getIdPosicionIvaArca();
        }

        // Excepción propia de TSA
        if (idPosicionIvaArca == 6) {
            idPosicionIvaArca = 5;
        }

        int tipoDocumento = 80;
        String documento = cliente.getCuit().replace("-", "").trim();
        log.debug("tipo_documento={} - numero_documento={} (1)", tipoDocumento, documento);
        if (documento.isEmpty()) {
            documento = "0";
        }
        log.debug("tipo_documento={} - numero_documento={} (2)", tipoDocumento, documento);
        if (cliente.getTipoDocumento().trim().toUpperCase().startsWith("PAS")) {
            tipoDocumento = 94;
            documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
            log.debug("tipo_documento={} - numero_documento={} (3)", tipoDocumento, documento);
        } else {
            if (Long.parseLong(documento) == 0) {
                tipoDocumento = 96;
                documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
                documento = documento.substring(0, Math.min(documento.length(), 8));
                log.debug("tipo_documento={} - numero_documento={} (4)", tipoDocumento, documento);
            }

            if (Long.parseLong(documento) == 0) {
                tipoDocumento = 99;
                documento = "0";
                log.debug("tipo_documento={} - numero_documento={} (5)", tipoDocumento, documento);
            }
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal total21 = BigDecimal.ZERO;
        BigDecimal total105 = BigDecimal.ZERO;
        BigDecimal exento = BigDecimal.ZERO;
        for (ReservaArticulo reservaArticulo : reservaArticuloService.findAllByReservaId(reservaId)) {
            reservaArticulo.setArticulo(articuloService.findByArticuloId(reservaArticulo.getArticuloId()));
            log.debug("ReservaArticulo -> {}", reservaArticulo.jsonify());
            BigDecimal subtotal = reservaArticulo.getPrecioUnitario().multiply(new BigDecimal(reservaArticulo.getCantidad()));
            total = total.add(subtotal);
            if (Objects.requireNonNull(reservaArticulo.getArticulo()).getExento() == (byte) 1) {
                exento = exento.add(subtotal);
            } else if (reservaArticulo.getArticulo().getIva105() == (byte) 1) {
                total105 = total105.add(subtotal);
            } else {
                total21 = total21.add(subtotal);
            }
        }

        // actualiza reserva_context
        ReservaContext reservaContext;
        try {
            reservaContext = reservaContextService.findByVoucherId(reserva.getVoucherId());
            reservaContext.setFacturaTries(1 + reservaContext.getFacturaTries());
        } catch (ReservaContextException e) {
            log.debug("creando reserva_context");
            reservaContext = ReservaContext.builder()
                    .reservaId(reserva.getReservaId())
                    .voucherId(reserva.getVoucherId())
                    .orderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())))
                    .facturaPendiente((byte) 1)
                    .envioPendiente((byte) 1)
                    .build();
            reservaContext = reservaContextService.add(reservaContext);
        }
        log.debug("ReservaContext -> {}", reservaContext.jsonify());

        // Chequeo si la reserva ya pasó por ARCA
        if (reservaContext.getFacturaArca() == (byte) 1) {
            log.info("Reserva {} Order Number {} facturada en ARCA sin registro", reservaId, reservaContext.getOrderNumberId());
            return false;
        }

        BigDecimal coeficienteIva1 = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        BigDecimal neto21 = total21.divide(BigDecimal.ONE.add(coeficienteIva1), 5, RoundingMode.HALF_UP);
        BigDecimal coeficienteIva2 = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        BigDecimal neto105 = total105.divide(BigDecimal.ONE.add(coeficienteIva2), 5, RoundingMode.HALF_UP);
        BigDecimal iva21 = neto21.multiply(coeficienteIva1).setScale(5, RoundingMode.HALF_UP);
        log.debug("total21={} - neto21={} - coeficienteIva1={} - iva21={}", total21, neto21, coeficienteIva1, iva21);
        BigDecimal iva105 = neto105.multiply(coeficienteIva2).setScale(5, RoundingMode.HALF_UP);
        log.debug("total105={} - neto105={} - coeficienteIva2={} - iva105={}", total105, neto105, coeficienteIva2, iva105);

        assert comprobante.getComprobanteAfipId() != null;
        FacturacionDto facturacionDto = FacturacionDto.builder()
                .tipoDocumento(tipoDocumento)
                .documento(documento)
                .tipoAfip(comprobante.getComprobanteAfipId())
                .puntoVenta(comprobante.getPuntoVenta())
                .total(total.setScale(2, RoundingMode.HALF_UP))
                .exento(exento.setScale(2, RoundingMode.HALF_UP))
                .neto(neto21.setScale(2, RoundingMode.HALF_UP))
                .neto105(neto105.setScale(2, RoundingMode.HALF_UP))
                .iva(iva21.setScale(2, RoundingMode.HALF_UP))
                .iva105(iva105.setScale(2, RoundingMode.HALF_UP))
                .idCondicionIva(idPosicionIvaArca)
                .build();
        log.debug("FacturacionDto -> {}", facturacionDto.jsonify());

        try {
            facturacionDto = facturacionElectronicaService.makeFactura(facturacionDto);
            log.debug("After makeFactura -> {}", facturacionDto.jsonify());
        } catch (WebClientResponseException e) {
            log.debug("Servicio de Facturación NO disponible");
            reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
            log.debug("ReservaContext -> {}", reservaContext.jsonify());
            return false;
        }

        if (!facturacionDto.getResultado().equals("A")) {
            reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
            log.debug("Facturación NO Aprobada por ARCA -> {}", reservaContext.jsonify());
            return false;
        }

        var orderNote = orderNoteService.findByOrderNumberId(reservaContext.getOrderNumberId());
        log.debug("Recuperando order_note -> {}", orderNote.jsonify());

        reservaContext = registraFacturaService.markReservaContextFacturada(reservaContext, orderNote, facturacionDto);

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
                .comprobanteId(comprobanteId)
                .puntoVenta(facturacionDto.getPuntoVenta())
                .numeroComprobante(facturacionDto.getNumeroComprobante())
                .clienteId(cliente.getClienteId())
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
                .trackUuid(track.getUuid())
                .build();
        registroCae = registroCaeService.add(registroCae);
        log.debug("RegistroCae -> {}", registroCae.jsonify());

        ClienteMovimiento clienteMovimiento = null;
        try {
            clienteMovimiento = facturacionService.registraTransaccionFacturaProgramaDia(
                    reserva,
                    facturacionDto,
                    comprobante,
                    empresa,
                    cliente,
                    parametro,
                    reservaContext,
                    track,
                    false
            );
            track = trackService.endTracking(track);
            log.debug("Track -> {}", track.jsonify());
        } catch (Exception e) {
            track = trackService.failedTracking(track);
            log.debug("Track -> {}", track.jsonify());
        }

        if (clienteMovimiento != null) {
            var enableSendEmail = Boolean.parseBoolean(environment.getProperty("app.enable-send-email", "true"));
            if (enableSendEmail) {
                if (clienteMovimiento.getClienteMovimientoId() != null) {
                    try {
                        reservaContext.setEnvioTries(1 + reservaContext.getEnvioTries());
                        log.debug("envío correo={}", makeFacturaReportClient.send(clienteMovimiento.getClienteMovimientoId(), "daniel.quinterospinto@gmail.com"));
                        reservaContext.setEnvioPendiente((byte) 0);
                        reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
                        log.debug("Ok send ReservaContext -> {}", reservaContext.jsonify());
                        return true;
                    } catch (MessagingException e) {
                        reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
                        log.debug("Error send ReservaContext -> {}", reservaContext.jsonify());
                    }
                }
            }
        }

        return false;

    }

}
