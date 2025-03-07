package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.client.report.MakeFacturaReportClient;
import eterea.core.service.kotlin.exception.ReservaContextException;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.FacturacionElectronicaService;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MakeFacturaProgramaDiaService {

    private final ComprobanteService comprobanteService;
    private final ReservaService reservaService;
    private final EmpresaService empresaService;
    private final ReservaArticuloService reservaArticuloService;
    private final ParametroService parametroService;
    private final FacturacionElectronicaService facturacionElectronicaService;
    private final CuentaMovimientoService cuentaMovimientoService;
    private final VoucherService voucherService;
    private final ClienteMovimientoService clienteMovimientoService;
    private final ValorService valorService;
    private final ValorMovimientoService valorMovimientoService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final RegistroCaeService registroCaeService;
    private final ClienteService clienteService;
    private final ArticuloService articuloService;
    private final ReservaContextService reservaContextService;
    private final OrderNoteService orderNoteService;
    private final MakeFacturaReportClient makeFacturaReportClient;
    private final Environment environment;

    public MakeFacturaProgramaDiaService(ComprobanteService comprobanteService, ReservaService reservaService, EmpresaService empresaService,
                                         ReservaArticuloService reservaArticuloService, ParametroService parametroService,
                                         FacturacionElectronicaService facturacionElectronicaService, CuentaMovimientoService cuentaMovimientoService,
                                         VoucherService voucherService, ClienteMovimientoService clienteMovimientoService, ValorService valorService,
                                         ValorMovimientoService valorMovimientoService, ArticuloMovimientoService articuloMovimientoService,
                                         RegistroCaeService registroCaeService, ClienteService clienteService, ArticuloService articuloService,
                                         ReservaContextService reservaContextService, OrderNoteService orderNoteService, MakeFacturaReportClient makeFacturaReportClient,
                                         Environment environment) {
        this.comprobanteService = comprobanteService;
        this.reservaService = reservaService;
        this.empresaService = empresaService;
        this.reservaArticuloService = reservaArticuloService;
        this.parametroService = parametroService;
        this.facturacionElectronicaService = facturacionElectronicaService;
        this.cuentaMovimientoService = cuentaMovimientoService;
        this.voucherService = voucherService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.valorService = valorService;
        this.valorMovimientoService = valorMovimientoService;
        this.articuloMovimientoService = articuloMovimientoService;
        this.registroCaeService = registroCaeService;
        this.clienteService = clienteService;
        this.articuloService = articuloService;
        this.reservaContextService = reservaContextService;
        this.orderNoteService = orderNoteService;
        this.makeFacturaReportClient = makeFacturaReportClient;
        this.environment = environment;
    }

    public boolean facturaReserva(Long reservaId, Integer comprobanteId) {
        Comprobante comprobante = comprobanteService.findByComprobanteId(comprobanteId);
        if (comprobante.getFacturaElectronica() == 0) {
            return false;
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
            return false;
        }
        Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
        logVoucher(voucher);
        Cliente cliente = clienteService.findByClienteId(reserva.getClienteId());
        logCliente(cliente);

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
            BigDecimal subtotal = reservaArticulo.getPrecioUnitario().multiply(new BigDecimal(reservaArticulo.getCantidad()));
            total = total.add(subtotal);
            if (reservaArticulo.getArticulo().getExento() == (byte) 1) {
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
                    .orderNumberId(Long.valueOf(voucher.getNumeroVoucher()))
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
        log.debug("total105={} - neto105={} - coeficienteIva2={} - iva105={}", total105, neto105, coeficienteIva2, iva105);

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
            log.debug("After makeFactura");
            logFacturacionDto(facturacionDto);
        } catch (WebClientResponseException e) {
            log.debug("Servicio de Facturación NO disponible");
            reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
            return false;
        }

        if (!facturacionDto.getResultado().equals("A")) {
            reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
            return false;
        }

        var orderNote = orderNoteService.findByOrderNumberId(reservaContext.getOrderNumberId());
        logOrderNote(orderNote);

        reservaContext.setFacturaPendiente((byte) 0);
        reservaContext.setDiferenciaWeb(orderNote.getOrderTotal().subtract(facturacionDto.getTotal()).setScale(2, RoundingMode.HALF_UP));
        // Convierte fechas
        SimpleDateFormat formatoInDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat formatoOutDate = new SimpleDateFormat("ddMMyyyy");
        Date vencimientoCae = null;
        try {
            vencimientoCae = formatoInDate.parse(facturacionDto.getVencimientoCae());
        } catch (ParseException e) {
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
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
                .fecha(ToolService.dateAbsoluteArgentina().format(dateTimeFormatter))
                .caeVencimiento(formatoOutDate.format(vencimientoCae))
                .tipoDocumento(facturacionDto.getTipoDocumento())
                .numeroDocumento(new BigDecimal(facturacionDto.getDocumento()))
                .build();
        registroCae = registroCaeService.add(registroCae);
        logRegistroCae(registroCae);

        ClienteMovimiento clienteMovimiento = registraTransaccionFactura(reserva, facturacionDto, comprobante, empresa, cliente, parametro, reservaContext);

        var enableSendEmail = Boolean.parseBoolean(environment.getProperty("app.enable-send-email", "true"));
        if (enableSendEmail) {
            if (clienteMovimiento.getClienteMovimientoId() != null) {
                logReservaContext(reservaContext);

                try {
                    reservaContext.setEnvioTries(1 + reservaContext.getEnvioTries());
                    log.debug("envío correo={}", makeFacturaReportClient.send(clienteMovimiento.getClienteMovimientoId(), "daniel.quinterospinto@gmail.com"));
                    reservaContext.setEnvioPendiente((byte) 0);
                    reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
                    return true;
                } catch (MessagingException e) {
                    reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
                }
            }
        }

        return false;

    }

    private void logOrderNote(OrderNote orderNote) {
        try {
            log.debug("order_note={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(orderNote));
        } catch (JsonProcessingException e) {
            log.debug("order_note=null");
        }
    }

    private void logFacturacionDto(FacturacionDto facturacionDto) {
        try {
            log.debug("facturacionDto={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(facturacionDto));
        } catch (JsonProcessingException e) {
            log.debug("facturacionDto=null");
        }
    }

    private void logRegistroCae(RegistroCae registroCae) {
        try {
            log.debug("registroCae={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(registroCae));
        } catch (JsonProcessingException e) {
            log.debug("registroCae=null");
        }
    }

    private void logReservaArticulo(ReservaArticulo reservaArticulo) {
        try {
            log.debug("reservaArticulo={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(reservaArticulo));
        } catch (JsonProcessingException e) {
            log.debug("reservaArticulo=null");
        }
    }

    private void logCliente(Cliente cliente) {
        try {
            log.debug("cliente={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(cliente));
        } catch (JsonProcessingException e) {
            log.debug("cliente=null");
        }
    }

    private void logVoucher(Voucher voucher) {
        try {
            log.debug("voucher={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(voucher));
        } catch (JsonProcessingException e) {
            log.debug("voucher=null");
        }
    }

    private void logReservaContext(ReservaContext reservaContext) {
        try {
            log.debug("reserva_context={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(reservaContext));
        } catch (JsonProcessingException e) {
            log.debug("reserva_context=null");
        }
    }

    private void logReserva(Reserva reserva) {
        try {
            log.debug("reserva={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(reserva));
        } catch (JsonProcessingException e) {
            log.debug("reserva=null");
        }
    }

    private void logParametro(Parametro parametro) {
        try {
            log.debug("parametro={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(parametro));
        } catch (JsonProcessingException e) {
            log.debug("parametro=null");
        }
    }

    private void logEmpresa(Empresa empresa) {
        try {
            log.debug("empresa={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(empresa));
        } catch (JsonProcessingException e) {
            log.debug("empresa=null");
        }
    }

    private void logComprobante(Comprobante comprobante) {
        try {
            log.debug("comprobante={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(comprobante));
        } catch (JsonProcessingException e) {
            log.debug("comprobante=null");
        }
    }

    @Transactional
    protected ClienteMovimiento registraTransaccionFactura(Reserva reserva, FacturacionDto facturacionDTO, Comprobante comprobante, Empresa empresa, Cliente cliente, Parametro parametro, ReservaContext reservaContext) {
        Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
        try {
            log.debug("voucher={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(voucher));
        } catch (JsonProcessingException e) {
            log.debug("voucher=null");
        }
        OrderNote orderNote = orderNoteService.findByOrderNumberId(Long.valueOf(voucher.getNumeroVoucher()));
        try {
            log.debug("orderNote={}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(orderNote));
        } catch (JsonProcessingException e) {
            log.debug("orderNote=null");
        }
        int valorId = switch (orderNote.getPayment().getMarcaTarjeta()) {
            case "American Express" -> 64;
            case "Cabal" -> 67;
            case "Cabal Du00e9bito" -> 66;
            case "Maestro" -> 61;
            case "MasterCard" -> 62;
            case "MasterCard Debito" -> 61;
            case "Tarjeta Naranja" -> 60;
            case "Visa Cru00e9dito" -> 60;
            case "Visa Debito" -> 59;
            default -> 0;
        };
        Valor valor = valorService.findByValorId(valorId);

        String observaciones = "Pedido web #" + orderNote.getOrderNumberId() + " - Reserva #" + reserva.getReservaId();
        // Registra clienteMovimiento
        ClienteMovimiento clienteMovimiento = new ClienteMovimiento.Builder()
                .negocioId(empresa.getNegocioId())
                .empresaId(empresa.getEmpresaId())
                .clienteId(cliente.getClienteId())
                .comprobanteId(comprobante.getComprobanteId())
                .fechaComprobante(ToolService.dateAbsoluteArgentina())
                .fechaVencimiento(ToolService.dateAbsoluteArgentina())
                .importe(facturacionDTO.getTotal())
                .cancelado(facturacionDTO.getTotal())  // contado
                .puntoVenta(comprobante.getPuntoVenta())
                .numeroComprobante(facturacionDTO.getNumeroComprobante())
                .montoIva(facturacionDTO.getIva())
                .montoIvaRni(facturacionDTO.getIva105())
                .neto(facturacionDTO.getNeto())
                .letraComprobante(comprobante.getLetraComprobante())
                .montoExento(facturacionDTO.getExento())
                .reservaId(reserva.getReservaId())
                .cae(facturacionDTO.getCae())
                .caeVencimiento(facturacionDTO.getVencimientoCae())
                .monedaId(1)
                .cotizacion(BigDecimal.ONE)
                .letras(ToolService.number_2_text(facturacionDTO.getTotal()))
                .observaciones(observaciones)
                .build();
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);

        // Registra reservaContext
        reservaContext.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());

        // Registra valorMovimiento
        ValorMovimiento valorMovimiento = new ValorMovimiento.Builder()
                .negocioId(empresa.getNegocioId())
                .clienteId(cliente.getClienteId())
                .proveedorId(0L)
                .comprobanteId(comprobante.getComprobanteId())
                .fechaEmision(clienteMovimiento.getFechaComprobante())
                .fechaVencimiento(clienteMovimiento.getFechaComprobante())
                .valorId(valorId)
                .numeroComprobante(0L)
                .importe(facturacionDTO.getTotal())
                .numeroCuenta(valor.getNumeroCuenta())
                .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
                .proveedorMovimientoId(0L)
                .titular("")
                .banco("")
                .receptor("")
                .estadoId(0)
                .cierreCajaId(0L)
                .observaciones(observaciones)
                .build();
        valorMovimiento = valorMovimientoService.add(valorMovimiento);

        List<ArticuloMovimiento> articuloMovimientos = new ArrayList<>();
        int item = 1;
        for (ReservaArticulo reservaArticulo : reservaArticuloService.findAllByReservaId(reserva.getReservaId())) {
            articuloMovimientos.add(new ArticuloMovimiento.Builder()
                    .clienteMovimientoId(clienteMovimiento.getClienteMovimientoId())
                    .centroStockId(reservaArticulo.getArticulo().getCentroStockId())
                    .comprobanteId(comprobante.getComprobanteId())
                    .item(item++)
                    .articuloId(reservaArticulo.getArticuloId())
                    .negocioId(clienteMovimiento.getNegocioId())
                    .cantidad(new BigDecimal(-1 * reservaArticulo.getCantidad()))
                    .precioUnitario(reservaArticulo.getPrecioUnitario())
                    .precioUnitarioSinIva(calcularPrecioSinIva(reservaArticulo.getPrecioUnitario(), reservaArticulo.getArticulo().getIva105(), reservaArticulo.getArticulo().getExento(), parametro))
                    .precioUnitarioConIva(reservaArticulo.getPrecioUnitario())
                    .numeroCuenta(reservaArticulo.getArticulo().getCuentaVentas())
                    .iva105(reservaArticulo.getArticulo().getIva105())
                    .exento(reservaArticulo.getArticulo().getExento())
                    .fechaMovimiento(clienteMovimiento.getFechaComprobante())
                    .fechaFactura(clienteMovimiento.getFechaComprobante())
                    .precioCompra(reservaArticulo.getArticulo().getPrecioCompra())
                    .build());
        }
        articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

        List<CuentaMovimiento> clienteMovimientos = registraContabilidad(clienteMovimiento, valorMovimiento, valor, articuloMovimientos, facturacionDTO, comprobante, parametro);

        reserva.setFacturada((byte) 1);
        reserva.setVerificada((byte) 1);
        reserva = reservaService.update(reserva, reserva.getReservaId());

        voucher.setConfirmado((byte) 1);
        voucher = voucherService.update(voucher, voucher.getVoucherId());

        return clienteMovimiento;
    }

    private BigDecimal calcularPrecioSinIva(BigDecimal precioUnitario, byte iva105, byte exento, Parametro parametro) {
        if (exento == 1) {
            return precioUnitario;
        }
        var coeficiente = parametro.getIva1().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        if (iva105 == 1) {
            coeficiente = parametro.getIva2().divide(new BigDecimal(100), 3, RoundingMode.HALF_UP);
        }
        var precioUnitarioSinIva = precioUnitario.divide(BigDecimal.ONE.add(coeficiente), 5, RoundingMode.HALF_UP);
        return precioUnitarioSinIva.setScale(2, RoundingMode.HALF_UP);
    }

    @Transactional
    protected List<CuentaMovimiento> registraContabilidad(ClienteMovimiento clienteMovimiento, ValorMovimiento valorMovimiento, Valor valor, List<ArticuloMovimiento> articuloMovimientos, FacturacionDto facturacionDTO, Comprobante comprobante, Parametro parametro) {
        List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
        int ordenContable = cuentaMovimientoService.nextOrdenContable(clienteMovimiento.getFechaComprobante());
        // Agrego asiento contable a clienteMovimiento
        clienteMovimiento.setFechaContable(clienteMovimiento.getFechaComprobante());
        clienteMovimiento.setOrdenContable(ordenContable);
        clienteMovimiento = clienteMovimientoService.update(clienteMovimiento, clienteMovimiento.getClienteMovimientoId());
        // Agrego asiento contable a valorMovimiento
        valorMovimiento.setFechaContable(clienteMovimiento.getFechaContable());
        valorMovimiento.setOrdenContable(ordenContable);
        valorMovimiento = valorMovimientoService.update(valorMovimiento, valorMovimiento.getValorMovimientoId());
        int item = 1;
        String concepto = String.format("Nro: %04d %06d", facturacionDTO.getPuntoVenta(), facturacionDTO.getNumeroComprobante());
        // Registro total valores
        cuentaMovimientos.add(new CuentaMovimiento.Builder()
                .negocioId(clienteMovimiento.getNegocioId())
                .numeroCuenta(valor.getNumeroCuenta())
                .debita(comprobante.getDebita())
                .importe(facturacionDTO.getTotal())
                .item(item++)
                .fecha(clienteMovimiento.getFechaComprobante())
                .comprobanteId(comprobante.getComprobanteId())
                .orden(ordenContable)
                .clienteId(clienteMovimiento.getClienteId())
                .subrubroId(2L)
                .concepto(concepto)
                .build());
        // Registro iva 21
        if (facturacionDTO.getIva().compareTo(BigDecimal.ZERO) > 0) {
            cuentaMovimientos.add(new CuentaMovimiento.Builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(parametro.getCuentaIvaVentas())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(facturacionDTO.getIva())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .build());
        }
        // Registro iva 10.5
        if (facturacionDTO.getIva105().compareTo(BigDecimal.ZERO) > 0) {
            cuentaMovimientos.add(new CuentaMovimiento.Builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(parametro.getCuentaIvaRniVentas())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(facturacionDTO.getIva105())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .build());
        }
        // Registro de artículos
        for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
            cuentaMovimientos.add(new CuentaMovimiento.Builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(articuloMovimiento.getNumeroCuenta())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(articuloMovimiento.getPrecioUnitarioSinIva().multiply(articuloMovimiento.getCantidad()).setScale(2, RoundingMode.HALF_UP).abs())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .articuloMovimientoId(articuloMovimiento.getArticuloMovimientoId())
                    .build());
        }

        cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);

        return cuentaMovimientos;
    }

}
