package eterea.core.service.service.facade;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

import eterea.core.service.client.report.MakeFacturaReportClient;
import eterea.core.service.kotlin.exception.ReservaContextException;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.Empresa;
import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.kotlin.model.RegistroCae;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.kotlin.model.ReservaContext;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.model.PosicionIva;
import eterea.core.service.model.dto.FacturaMakeRequestDto;
import eterea.core.service.service.ArticuloService;
import eterea.core.service.service.ClienteService;
import eterea.core.service.service.ComprobanteService;
import eterea.core.service.service.EmpresaService;
import eterea.core.service.service.ParametroService;
import eterea.core.service.service.RegistroCaeService;
import eterea.core.service.service.ReservaArticuloService;
import eterea.core.service.service.ReservaContextService;
import eterea.core.service.service.ReservaService;
import eterea.core.service.service.ValorService;
import eterea.core.service.service.VoucherService;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import eterea.core.service.tool.ToolService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

/*
 * @author: Sebastian 
 */
@Service
@Slf4j
public class MakeFacturaService {

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
   private final MakeFacturaReportClient makeFacturaReportClient;
   private final Environment environment;
   private final VoucherService voucherService;
   private final FacturacionService facturacionService;
   private final ValorService valorService;

   public MakeFacturaService(
         ComprobanteService comprobanteService,
         ReservaService reservaService,
         EmpresaService empresaService,
         ReservaArticuloService reservaArticuloService,
         ParametroService parametroService,
         FacturacionElectronicaService facturacionElectronicaService,
         RegistroCaeService registroCaeService,
         ClienteService clienteService,
         ArticuloService articuloService,
         ReservaContextService reservaContextService,
         MakeFacturaReportClient makeFacturaReportClient,
         Environment environment,
         VoucherService voucherService,
         FacturacionService facturacionService,
         ValorService valorService) {
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
      this.makeFacturaReportClient = makeFacturaReportClient;
      this.environment = environment;
      this.voucherService = voucherService;
      this.facturacionService = facturacionService;
      this.valorService = valorService;
   }

   // public Long facturaReserva(Long reservaId, Integer comprobanteId, Integer valorId) {
   //    Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
   //    if (comprobante.getFacturaElectronica() == 0) {
   //       return null;
   //    }
   //    logComprobante(comprobante);
   //    Empresa empresa = empresaService.findTop();
   //    logEmpresa(empresa);
   //    Parametro parametro = parametroService.findTop();
   //    logParametro(parametro);
   //    String moneda = "PES";
   //    Reserva reserva = reservaService.findByReservaId(reservaId);
   //    logReserva(reserva);
   //    if (reserva.getFacturada() == (byte) 1) {
   //       log.debug("reserva facturada={}", reserva.getReservaId());
   //       try {
   //          var reservaContext = reservaContextService.findByReservaId(reservaId);
   //          reservaContext.setFacturadoFuera((byte) 1);
   //          reservaContext.setFacturaPendiente((byte) 0);
   //          reservaContext.setEnvioPendiente((byte) 0);
   //          reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
   //          logReservaContext(reservaContext);
   //       } catch (ReservaContextException e) {
   //          log.debug("No hay reserva_context para esta reserva");
   //       }
   //       return null;
   //    }
   //    Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
   //    logVoucher(voucher);
   //    Cliente cliente = clienteService.findByClienteId(reserva.getClienteId());
   //    logCliente(cliente);
   //    PosicionIva posicionIva = cliente.getPosicion();
   //    var idPosicionIvaArca = 5; // TODO: Ver que significa este valor
   //    if (posicionIva != null && posicionIva.getIdPosicionIvaArca() != null) {
   //       idPosicionIvaArca = posicionIva.getIdPosicionIvaArca();
   //    }

   //    int tipoDocumento = 80;
   //    String documento = cliente.getCuit().replace("-", "").trim();
   //    log.debug("tipo_documento={} - numero_documento={} (1)", tipoDocumento, documento);
   //    if (documento.isEmpty()) {
   //       documento = "0";
   //    }
   //    log.debug("tipo_documento={} - numero_documento={} (2)", tipoDocumento, documento);
   //    if (cliente.getTipoDocumento().trim().toUpperCase().startsWith("PAS")) {
   //       tipoDocumento = 94;
   //       documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
   //       log.debug("tipo_documento={} - numero_documento={} (3)", tipoDocumento, documento);
   //    } else {
   //       if (Long.parseLong(documento) == 0) {
   //          tipoDocumento = 96;
   //          documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
   //          documento = documento.substring(0, Math.min(documento.length(), 8));
   //          log.debug("tipo_documento={} - numero_documento={} (4)", tipoDocumento, documento);
   //       }

   //       if (Long.parseLong(documento) == 0) {
   //          tipoDocumento = 99;
   //          documento = "0";
   //          log.debug("tipo_documento={} - numero_documento={} (5)", tipoDocumento, documento);
   //       }
   //    }

   //    BigDecimal total = BigDecimal.ZERO;
   //    BigDecimal total21 = BigDecimal.ZERO;
   //    BigDecimal total105 = BigDecimal.ZERO;
   //    BigDecimal exento = BigDecimal.ZERO;
   //    for (ReservaArticulo reservaArticulo : reservaArticuloService.findAllByReservaId(reservaId)) {
   //       reservaArticulo.setArticulo(articuloService.findByArticuloId(reservaArticulo.getArticuloId()));
   //       logReservaArticulo(reservaArticulo);
   //       BigDecimal subtotal = reservaArticulo.getPrecioUnitario()
   //             .multiply(new BigDecimal(reservaArticulo.getCantidad()));
   //       total = total.add(subtotal);
   //       if (Objects.requireNonNull(reservaArticulo.getArticulo()).getExento() == (byte) 1) {
   //          exento = exento.add(subtotal);
   //       } else if (reservaArticulo.getArticulo().getIva105() == (byte) 1) {
   //          total105 = total105.add(subtotal);
   //       } else {
   //          total21 = total21.add(subtotal);
   //       }
   //    }

   //    // actualiza reserva_context
   //    ReservaContext reservaContext = null;
   //    try {
   //       reservaContext = reservaContextService.findByVoucherId(reserva.getVoucherId());
   //       reservaContext.setFacturaTries(1 + reservaContext.getFacturaTries());
   //    } catch (ReservaContextException e) {
   //       log.debug("creando reserva_context");
   //       reservaContext = new ReservaContext.Builder()
   //             .reservaId(reserva.getReservaId())
   //             .voucherId(reserva.getVoucherId())
   //             .orderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())))
   //             .facturaPendiente((byte) 1)
   //             .envioPendiente((byte) 1)
   //             .build();
   //       reservaContext = reservaContextService.add(reservaContext);
   //    }
   //    logReservaContext(reservaContext);

   //    BigDecimal coeficienteIva1 = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
   //    BigDecimal neto21 = total21.divide(BigDecimal.ONE.add(coeficienteIva1), 5, RoundingMode.HALF_UP);
   //    BigDecimal coeficienteIva2 = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
   //    BigDecimal neto105 = total105.divide(BigDecimal.ONE.add(coeficienteIva2), 5, RoundingMode.HALF_UP);
   //    BigDecimal iva21 = neto21.multiply(coeficienteIva1).setScale(5, RoundingMode.HALF_UP);
   //    log.debug("total21={} - neto21={} - coeficienteIva1={} - iva21={}", total21, neto21, coeficienteIva1, iva21);
   //    BigDecimal iva105 = neto105.multiply(coeficienteIva2).setScale(5, RoundingMode.HALF_UP);
   //    log.debug("total105={} - neto105={} - coeficienteIva2={} - iva105={}", total105, neto105, coeficienteIva2,
   //          iva105);

   //    assert comprobante.getComprobanteAfipId() != null;
   //    FacturacionDto facturacionDto = new FacturacionDto.Builder()
   //          .tipoDocumento(tipoDocumento)
   //          .documento(documento)
   //          .tipoAfip(comprobante.getComprobanteAfipId())
   //          .puntoVenta(comprobante.getPuntoVenta())
   //          .total(total.setScale(2, RoundingMode.HALF_UP))
   //          .exento(exento.setScale(2, RoundingMode.HALF_UP))
   //          .neto(neto21.setScale(2, RoundingMode.HALF_UP))
   //          .neto105(neto105.setScale(2, RoundingMode.HALF_UP))
   //          .iva(iva21.setScale(2, RoundingMode.HALF_UP))
   //          .iva105(iva105.setScale(2, RoundingMode.HALF_UP))
   //          .build();

   //    logFacturacionDto(facturacionDto);

   //    try {
   //       facturacionDto = facturacionElectronicaService.makeFactura(facturacionDto);
   //       log.info("After makeFactura");
   //       logFacturacionDto(facturacionDto);
   //    } catch (WebClientResponseException e) {
   //       log.info("Servicio de Facturación NO disponible");
   //       reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
   //       return null;
   //    }

   //    if (!facturacionDto.getResultado().equals("A")) {
   //       reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
   //       // #### QUITAR ESTE COMENTARIO EN PRODUCCION ####
   //       // return null;
   //    }

   //    reservaContext.setFacturaPendiente((byte) 0);

   //    // Convierte fechas
   //    SimpleDateFormat formatoInDate = new SimpleDateFormat("yyyyMMdd");
   //    SimpleDateFormat formatoOutDate = new SimpleDateFormat("ddMMyyyy");
   //    Date vencimientoCae = null;
   //    try {
   //       vencimientoCae = formatoInDate.parse(facturacionDto.getVencimientoCae());
   //    } catch (ParseException e) {
   //       log.error("Error al parsear vencimientoCae", e);
   //    }
   //    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
   //    // Registra el resultado de la AFIP
   //    RegistroCae registroCae = new RegistroCae.Builder()
   //          .comprobanteId(comprobanteId)
   //          .puntoVenta(facturacionDto.getPuntoVenta())
   //          .numeroComprobante(facturacionDto.getNumeroComprobante())
   //          .clienteId(cliente.getClienteId())
   //          .cuit("")
   //          .total(facturacionDto.getTotal())
   //          .exento(facturacionDto.getExento())
   //          .neto(facturacionDto.getNeto())
   //          .neto105(facturacionDto.getNeto105())
   //          .iva(facturacionDto.getIva())
   //          .iva105(facturacionDto.getIva105())
   //          .cae(facturacionDto.getCae())
   //          .fecha(ToolService.dateAbsoluteArgentina().format(dateTimeFormatter))
   //          .caeVencimiento(formatoOutDate.format(vencimientoCae))
   //          .tipoDocumento(facturacionDto.getTipoDocumento())
   //          .numeroDocumento(new BigDecimal(facturacionDto.getDocumento()))
   //          .build();
   //    registroCae = registroCaeService.add(registroCae);
   //    logRegistroCae(registroCae);

   //    Valor valor = valorService.findByValorId(valorId);
   //    log.debug("Valor FacturaReserva = {}", valor);

   //    ClienteMovimiento clienteMovimiento = facturacionService.registraTransaccionFactura(reserva,
   //          facturacionDto, comprobante, valor, empresa, cliente, parametro, reservaContext);

   //    // // Omito el envío de correo por ahora
   //    // if (clienteMovimiento != null) {
   //    // return clienteMovimiento.getClienteMovimientoId();
   //    // }

   //    var enableSendEmail = Boolean.parseBoolean(environment.getProperty("app.enable-send-email", "true"));
   //    if (enableSendEmail) {
   //       if (clienteMovimiento.getClienteMovimientoId() != null) {
   //          logReservaContext(reservaContext);

   //          try {
   //             if (reservaContext != null) {
   //                reservaContext.setEnvioTries(1 + reservaContext.getEnvioTries());
   //                reservaContext.setEnvioPendiente((byte) 0);
   //                reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
   //             }
   //             log.debug("envío correo={}", makeFacturaReportClient.send(clienteMovimiento.getClienteMovimientoId(),
   //                   "daniel.quinterospinto@gmail.com"));
   //             return clienteMovimiento.getClienteMovimientoId();
   //          } catch (MessagingException e) {
   //             if (reservaContext != null) {
   //                reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
   //             }
   //          }
   //       }
   //    }

   //    return null;

   // }

   // // Diferente metodo porque por ahora hotel no tiene voucher y por ende tampoco
   // // reservaContext
   // public Long facturaReservaHotel(Long reservaId, Integer comprobanteId, Integer valorId) {
   //    Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
   //    if (comprobante.getFacturaElectronica() == 0) {
   //       return null;
   //    }
   //    logComprobante(comprobante);
   //    Empresa empresa = empresaService.findTop();
   //    logEmpresa(empresa);
   //    Parametro parametro = parametroService.findTop();
   //    logParametro(parametro);
   //    String moneda = "PES";
   //    Reserva reserva = reservaService.findByReservaId(reservaId);
   //    logReserva(reserva);
   //    if (reserva.getFacturada() == (byte) 1) {
   //       log.debug("reserva facturada={}", reserva.getReservaId());
   //       return null;
   //    }

   //    Cliente cliente = clienteService.findByClienteId(reserva.getClienteId());
   //    logCliente(cliente);
   //    PosicionIva posicionIva = cliente.getPosicion();
   //    var idPosicionIvaArca = 5; // TODO: Ver que significa este valor
   //    if (posicionIva != null && posicionIva.getIdPosicionIvaArca() != null) {
   //       idPosicionIvaArca = posicionIva.getIdPosicionIvaArca();
   //    }

   //    int tipoDocumento = 80;
   //    String documento = cliente.getCuit().replace("-", "").trim();
   //    log.debug("tipo_documento={} - numero_documento={} (1)", tipoDocumento, documento);
   //    if (documento.isEmpty()) {
   //       documento = "0";
   //    }
   //    log.debug("tipo_documento={} - numero_documento={} (2)", tipoDocumento, documento);
   //    if (cliente.getTipoDocumento().trim().toUpperCase().startsWith("PAS")) {
   //       tipoDocumento = 94;
   //       documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
   //       log.debug("tipo_documento={} - numero_documento={} (3)", tipoDocumento, documento);
   //    } else {
   //       if (Long.parseLong(documento) == 0) {
   //          tipoDocumento = 96;
   //          documento = ToolService.onlyNumbers(cliente.getNumeroDocumento());
   //          documento = documento.substring(0, Math.min(documento.length(), 8));
   //          log.debug("tipo_documento={} - numero_documento={} (4)", tipoDocumento, documento);
   //       }

   //       if (Long.parseLong(documento) == 0) {
   //          tipoDocumento = 99;
   //          documento = "0";
   //          log.debug("tipo_documento={} - numero_documento={} (5)", tipoDocumento, documento);
   //       }
   //    }

   //    BigDecimal total = BigDecimal.ZERO;
   //    BigDecimal total21 = BigDecimal.ZERO;
   //    BigDecimal total105 = BigDecimal.ZERO;
   //    BigDecimal exento = BigDecimal.ZERO;
   //    for (ReservaArticulo reservaArticulo : reservaArticuloService.findAllByReservaId(reservaId)) {
   //       reservaArticulo.setArticulo(articuloService.findByArticuloId(reservaArticulo.getArticuloId()));
   //       logReservaArticulo(reservaArticulo);
   //       BigDecimal subtotal = reservaArticulo.getPrecioUnitario()
   //             .multiply(new BigDecimal(reservaArticulo.getCantidad()));
   //       total = total.add(subtotal);
   //       if (Objects.requireNonNull(reservaArticulo.getArticulo()).getExento() == (byte) 1) {
   //          exento = exento.add(subtotal);
   //       } else if (reservaArticulo.getArticulo().getIva105() == (byte) 1) {
   //          total105 = total105.add(subtotal);
   //       } else {
   //          total21 = total21.add(subtotal);
   //       }
   //    }

   //    BigDecimal coeficienteIva1 = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
   //    BigDecimal neto21 = total21.divide(BigDecimal.ONE.add(coeficienteIva1), 5, RoundingMode.HALF_UP);
   //    BigDecimal coeficienteIva2 = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
   //    BigDecimal neto105 = total105.divide(BigDecimal.ONE.add(coeficienteIva2), 5, RoundingMode.HALF_UP);
   //    BigDecimal iva21 = neto21.multiply(coeficienteIva1).setScale(5, RoundingMode.HALF_UP);
   //    log.debug("total21={} - neto21={} - coeficienteIva1={} - iva21={}", total21, neto21, coeficienteIva1, iva21);
   //    BigDecimal iva105 = neto105.multiply(coeficienteIva2).setScale(5, RoundingMode.HALF_UP);
   //    log.debug("total105={} - neto105={} - coeficienteIva2={} - iva105={}", total105, neto105, coeficienteIva2,
   //          iva105);

   //    assert comprobante.getComprobanteAfipId() != null;
   //    FacturacionDto facturacionDto = new FacturacionDto.Builder()
   //          .tipoDocumento(tipoDocumento)
   //          .documento(documento)
   //          .tipoAfip(comprobante.getComprobanteAfipId())
   //          .puntoVenta(comprobante.getPuntoVenta())
   //          .total(total.setScale(2, RoundingMode.HALF_UP))
   //          .exento(exento.setScale(2, RoundingMode.HALF_UP))
   //          .neto(neto21.setScale(2, RoundingMode.HALF_UP))
   //          .neto105(neto105.setScale(2, RoundingMode.HALF_UP))
   //          .iva(iva21.setScale(2, RoundingMode.HALF_UP))
   //          .iva105(iva105.setScale(2, RoundingMode.HALF_UP))
   //          .build();

   //    logFacturacionDto(facturacionDto);

   //    try {
   //       facturacionDto = facturacionElectronicaService.makeFactura(facturacionDto);
   //       log.info("After makeFactura");
   //       logFacturacionDto(facturacionDto);
   //    } catch (WebClientResponseException e) {
   //       log.info("Servicio de Facturación NO disponible");
   //       return null;
   //    }

   //    if (!facturacionDto.getResultado().equals("A")) {
   //       return null;
   //    }

   //    // Convierte fechas
   //    SimpleDateFormat formatoInDate = new SimpleDateFormat("yyyyMMdd");
   //    SimpleDateFormat formatoOutDate = new SimpleDateFormat("ddMMyyyy");
   //    Date vencimientoCae = null;
   //    try {
   //       vencimientoCae = formatoInDate.parse(facturacionDto.getVencimientoCae());
   //    } catch (ParseException e) {
   //       log.error("Error al parsear vencimientoCae", e);
   //    }
   //    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
   //    // Registra el resultado de la AFIP
   //    RegistroCae registroCae = new RegistroCae.Builder()
   //          .comprobanteId(comprobanteId)
   //          .puntoVenta(facturacionDto.getPuntoVenta())
   //          .numeroComprobante(facturacionDto.getNumeroComprobante())
   //          .clienteId(cliente.getClienteId())
   //          .cuit("")
   //          .total(facturacionDto.getTotal())
   //          .exento(facturacionDto.getExento())
   //          .neto(facturacionDto.getNeto())
   //          .neto105(facturacionDto.getNeto105())
   //          .iva(facturacionDto.getIva())
   //          .iva105(facturacionDto.getIva105())
   //          .cae(facturacionDto.getCae())
   //          .fecha(ToolService.dateAbsoluteArgentina().format(dateTimeFormatter))
   //          .caeVencimiento(formatoOutDate.format(vencimientoCae))
   //          .tipoDocumento(facturacionDto.getTipoDocumento())
   //          .numeroDocumento(new BigDecimal(facturacionDto.getDocumento()))
   //          .build();
   //    registroCae = registroCaeService.add(registroCae);
   //    logRegistroCae(registroCae);

   //    Valor valor = valorService.findByValorId(valorId);

   //    // TODO: Ver si puedo enviar cada valor a este metodo y funciona bien
   //    ClienteMovimiento clienteMovimiento = facturacionService.registraTransaccionFacturaHotel(reserva,
   //          facturacionDto, comprobante, valor, empresa, cliente, parametro);

   //    // // Omito el envío de correo por ahora
   //    // if (clienteMovimiento != null) {
   //    // return clienteMovimiento.getClienteMovimientoId();
   //    // }

   //    var enableSendEmail = Boolean.parseBoolean(environment.getProperty("app.enable-send-email", "true"));
   //    if (enableSendEmail) {
   //       if (clienteMovimiento.getClienteMovimientoId() != null) {
   //          try {
   //             log.debug("envío correo={}", makeFacturaReportClient.send(clienteMovimiento.getClienteMovimientoId(),
   //                   "daniel.quinterospinto@gmail.com"));
   //             return clienteMovimiento.getClienteMovimientoId();
   //          } catch (MessagingException e) {
   //             log.debug("Error sending email", e);
   //          }
   //       }
   //    }

   //    return null;
   // }

   public Long facturaReservaMultipago(
         Long reservaId,
         FacturaMakeRequestDto facturaMakeRequestDto) {

      log.debug("facturaMakeRequestDto={}", facturaMakeRequestDto);

      Comprobante comprobante = comprobanteService.findByComprobanteId(facturaMakeRequestDto.comprobanteId());
      if (comprobante.getFacturaElectronica() == 0) {
         return null;
      }
      logComprobante(comprobante);
      Empresa empresa = empresaService.findTop();
      logEmpresa(empresa);
      Parametro parametro = parametroService.findTop();
      logParametro(parametro);
      String moneda = "PES";
      Reserva reserva = reservaService.findByReservaId(reservaId);
      logReserva(reserva);
      if (reserva.getFacturada() == (byte) 1) {
         log.debug("reserva facturada={}", reserva.getReservaId());
         try {
            var reservaContext = reservaContextService.findByReservaId(reservaId);
            reservaContext.setFacturadoFuera((byte) 1);
            reservaContext.setFacturaPendiente((byte) 0);
            reservaContext.setEnvioPendiente((byte) 0);
            reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
            logReservaContext(reservaContext);
         } catch (ReservaContextException e) {
            log.debug("No hay reserva_context para esta reserva");
         }
         return null;
      }
      Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
      logVoucher(voucher);
      Cliente cliente = clienteService.findByClienteId(reserva.getClienteId());
      logCliente(cliente);
      PosicionIva posicionIva = cliente.getPosicion();
      var idPosicionIvaArca = 5; // TODO: Ver que significa este valor
      if (posicionIva != null && posicionIva.getIdPosicionIvaArca() != null) {
         idPosicionIvaArca = posicionIva.getIdPosicionIvaArca();
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
         logReservaArticulo(reservaArticulo);
         BigDecimal subtotal = reservaArticulo.getPrecioUnitario()
               .multiply(new BigDecimal(reservaArticulo.getCantidad()));
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
      ReservaContext reservaContext = null;
      try {
         reservaContext = reservaContextService.findByVoucherId(reserva.getVoucherId());
         reservaContext.setFacturaTries(1 + reservaContext.getFacturaTries());
      } catch (ReservaContextException e) {
         log.debug("creando reserva_context");
         reservaContext = new ReservaContext.Builder()
               .reservaId(reserva.getReservaId())
               .voucherId(reserva.getVoucherId())
               .orderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())))
               .facturaPendiente((byte) 1)
               .envioPendiente((byte) 1)
               .build();
         reservaContext = reservaContextService.add(reservaContext);
      }
      logReservaContext(reservaContext);

      BigDecimal coeficienteIva1 = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
      BigDecimal neto21 = total21.divide(BigDecimal.ONE.add(coeficienteIva1), 5, RoundingMode.HALF_UP);
      BigDecimal coeficienteIva2 = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
      BigDecimal neto105 = total105.divide(BigDecimal.ONE.add(coeficienteIva2), 5, RoundingMode.HALF_UP);
      BigDecimal iva21 = neto21.multiply(coeficienteIva1).setScale(5, RoundingMode.HALF_UP);
      log.debug("total21={} - neto21={} - coeficienteIva1={} - iva21={}", total21, neto21, coeficienteIva1, iva21);
      BigDecimal iva105 = neto105.multiply(coeficienteIva2).setScale(5, RoundingMode.HALF_UP);
      log.debug("total105={} - neto105={} - coeficienteIva2={} - iva105={}", total105, neto105, coeficienteIva2,
            iva105);

      assert comprobante.getComprobanteAfipId() != null;
      FacturacionDto facturacionDto = new FacturacionDto.Builder()
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
            .build();

      logFacturacionDto(facturacionDto);

      try {
         facturacionDto = facturacionElectronicaService.makeFactura(facturacionDto);
         log.info("After makeFactura");
         logFacturacionDto(facturacionDto);
      } catch (WebClientResponseException e) {
         log.info("Servicio de Facturación NO disponible");
         reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
         return null;
      }

      if (!facturacionDto.getResultado().equals("A")) {
         reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
         return null;
      }

      reservaContext.setFacturaPendiente((byte) 0);

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
      // Registra el resultado de la AFIP
      RegistroCae registroCae = new RegistroCae.Builder()
            .comprobanteId(facturaMakeRequestDto.comprobanteId())
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
            .fecha(ToolService.dateAbsoluteArgentina().format(dateTimeFormatter))
            .caeVencimiento(formatoOutDate.format(vencimientoCae))
            .tipoDocumento(facturacionDto.getTipoDocumento())
            .numeroDocumento(new BigDecimal(facturacionDto.getDocumento()))
            .build();
      registroCae = registroCaeService.add(registroCae);
      logRegistroCae(registroCae);

      ClienteMovimiento clienteMovimiento = facturacionService.registraTransaccionFacturaMultiplesPagos(
            reserva,
            facturacionDto,
            comprobante,
            facturaMakeRequestDto.valorMovimientos(),
            empresa,
            cliente,
            parametro,
            reservaContext);

      var enableSendEmail = Boolean.parseBoolean(environment.getProperty("app.enable-send-email", "true"));
      if (enableSendEmail) {
         if (clienteMovimiento.getClienteMovimientoId() != null) {
            logReservaContext(reservaContext);

            try {
               if (reservaContext != null) {
                  reservaContext.setEnvioTries(1 + reservaContext.getEnvioTries());
                  reservaContext.setEnvioPendiente((byte) 0);
                  reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
               }
               log.debug("envío correo={}", makeFacturaReportClient.send(clienteMovimiento.getClienteMovimientoId(),
                     "daniel.quinterospinto@gmail.com"));
               return clienteMovimiento.getClienteMovimientoId();
            } catch (MessagingException e) {
               if (reservaContext != null) {
                  reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
               }
            }
         }
      }
      return null;
   }

   public Long facturaReservaHotelMultipago(
         Long reservaId,
         FacturaMakeRequestDto facturaMakeRequestDto) {

      Comprobante comprobante = comprobanteService.findByComprobanteId(facturaMakeRequestDto.comprobanteId());
      if (comprobante.getFacturaElectronica() == 0) {
         return null;
      }
      logComprobante(comprobante);
      Empresa empresa = empresaService.findTop();
      logEmpresa(empresa);
      Parametro parametro = parametroService.findTop();
      logParametro(parametro);
      String moneda = "PES";
      Reserva reserva = reservaService.findByReservaId(reservaId);
      logReserva(reserva);
      if (reserva.getFacturada() == (byte) 1) {
         log.debug("reserva facturada={}", reserva.getReservaId());
         return null;
      }

      Cliente cliente = clienteService.findByClienteId(reserva.getClienteId());
      logCliente(cliente);
      PosicionIva posicionIva = cliente.getPosicion();
      var idPosicionIvaArca = 5; // TODO: Ver que significa este valor
      if (posicionIva != null && posicionIva.getIdPosicionIvaArca() != null) {
         idPosicionIvaArca = posicionIva.getIdPosicionIvaArca();
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
         logReservaArticulo(reservaArticulo);
         BigDecimal subtotal = reservaArticulo.getPrecioUnitario()
               .multiply(new BigDecimal(reservaArticulo.getCantidad()));
         total = total.add(subtotal);
         if (Objects.requireNonNull(reservaArticulo.getArticulo()).getExento() == (byte) 1) {
            exento = exento.add(subtotal);
         } else if (reservaArticulo.getArticulo().getIva105() == (byte) 1) {
            total105 = total105.add(subtotal);
         } else {
            total21 = total21.add(subtotal);
         }
      }

      BigDecimal coeficienteIva1 = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
      BigDecimal neto21 = total21.divide(BigDecimal.ONE.add(coeficienteIva1), 5, RoundingMode.HALF_UP);
      BigDecimal coeficienteIva2 = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
      BigDecimal neto105 = total105.divide(BigDecimal.ONE.add(coeficienteIva2), 5, RoundingMode.HALF_UP);
      BigDecimal iva21 = neto21.multiply(coeficienteIva1).setScale(5, RoundingMode.HALF_UP);
      log.debug("total21={} - neto21={} - coeficienteIva1={} - iva21={}", total21, neto21, coeficienteIva1, iva21);
      BigDecimal iva105 = neto105.multiply(coeficienteIva2).setScale(5, RoundingMode.HALF_UP);
      log.debug("total105={} - neto105={} - coeficienteIva2={} - iva105={}", total105, neto105, coeficienteIva2,
            iva105);

      assert comprobante.getComprobanteAfipId() != null;
      FacturacionDto facturacionDto = new FacturacionDto.Builder()
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
            .build();

      logFacturacionDto(facturacionDto);

      try {
         facturacionDto = facturacionElectronicaService.makeFactura(facturacionDto);
         log.info("After makeFactura");
         logFacturacionDto(facturacionDto);
      } catch (WebClientResponseException e) {
         log.info("Servicio de Facturación NO disponible");
         return null;
      }

      if (!facturacionDto.getResultado().equals("A")) {
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
      // Registra el resultado de la AFIP
      RegistroCae registroCae = new RegistroCae.Builder()
            .comprobanteId(facturaMakeRequestDto.comprobanteId())
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
            .fecha(ToolService.dateAbsoluteArgentina().format(dateTimeFormatter))
            .caeVencimiento(formatoOutDate.format(vencimientoCae))
            .tipoDocumento(facturacionDto.getTipoDocumento())
            .numeroDocumento(new BigDecimal(facturacionDto.getDocumento()))
            .build();
      registroCae = registroCaeService.add(registroCae);
      logRegistroCae(registroCae);

      ClienteMovimiento clienteMovimiento = facturacionService.registraTransaccionFacturaHotelMultiplesPagos(
            reserva,
            facturacionDto,
            comprobante,
            facturaMakeRequestDto.valorMovimientos(),
            empresa,
            cliente,
            parametro);

      var enableSendEmail = Boolean.parseBoolean(environment.getProperty("app.enable-send-email", "true"));
      if (enableSendEmail) {
         if (clienteMovimiento.getClienteMovimientoId() != null) {
            try {
               log.debug("envío correo={}", makeFacturaReportClient.send(clienteMovimiento.getClienteMovimientoId(),
                     "daniel.quinterospinto@gmail.com"));
               return clienteMovimiento.getClienteMovimientoId();
            } catch (MessagingException e) {
               log.debug("Error sending email", e);
            }
         }
      }

      return null;
   }

   private void logFacturacionDto(FacturacionDto facturacionDto) {
      try {
         log.debug("facturacionDto={}", JsonMapper.builder().findAndAddModules().build()
               .writerWithDefaultPrettyPrinter().writeValueAsString(facturacionDto));
      } catch (JsonProcessingException e) {
         log.debug("facturacionDto=null");
      }
   }

   private void logRegistroCae(RegistroCae registroCae) {
      try {
         log.debug("registroCae={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(registroCae));
      } catch (JsonProcessingException e) {
         log.debug("registroCae=null");
      }
   }

   private void logReservaArticulo(ReservaArticulo reservaArticulo) {
      try {
         log.debug("reservaArticulo={}", JsonMapper.builder().findAndAddModules().build()
               .writerWithDefaultPrettyPrinter().writeValueAsString(reservaArticulo));
      } catch (JsonProcessingException e) {
         log.debug("reservaArticulo=null");
      }
   }

   private void logCliente(Cliente cliente) {
      try {
         log.debug("cliente={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(cliente));
      } catch (JsonProcessingException e) {
         log.debug("cliente=null");
      }
   }

   private void logVoucher(Voucher voucher) {
      try {
         log.debug("voucher={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(voucher));
      } catch (JsonProcessingException e) {
         log.debug("voucher=null");
      }
   }

   private void logReservaContext(ReservaContext reservaContext) {
      try {
         log.debug("reserva_context={}", JsonMapper.builder().findAndAddModules().build()
               .writerWithDefaultPrettyPrinter().writeValueAsString(reservaContext));
      } catch (JsonProcessingException e) {
         log.debug("reserva_context=null");
      }
   }

   private void logReserva(Reserva reserva) {
      try {
         log.debug("reserva={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(reserva));
      } catch (JsonProcessingException e) {
         log.debug("reserva=null");
      }
   }

   private void logParametro(Parametro parametro) {
      try {
         log.debug("parametro={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(parametro));
      } catch (JsonProcessingException e) {
         log.debug("parametro=null");
      }
   }

   private void logEmpresa(Empresa empresa) {
      try {
         log.debug("empresa={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(empresa));
      } catch (JsonProcessingException e) {
         log.debug("empresa=null");
      }
   }

   private void logComprobante(Comprobante comprobante) {
      try {
         log.debug("comprobante={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter()
               .writeValueAsString(comprobante));
      } catch (JsonProcessingException e) {
         log.debug("comprobante=null");
      }
   }
}