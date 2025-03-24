package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.service.*;
import eterea.core.service.service.extern.OrderNoteService;
import eterea.core.service.tool.ToolService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
    private final ValorMovimientoService valorMovimientoService;
    private final ReservaArticuloService reservaArticuloService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final ReservaService reservaService;
    private final ContabilidadService contabilidadService;

    public FacturacionService(VoucherService voucherService,
                              OrderNoteService orderNoteService,
                              ValorService valorService,
                              ClienteMovimientoService clienteMovimientoService,
                              ReservaContextService reservaContextService,
                              ValorMovimientoService valorMovimientoService,
                              ReservaArticuloService reservaArticuloService,
                              ArticuloMovimientoService articuloMovimientoService,
                              ReservaService reservaService,
                              ContabilidadService contabilidadService) {
        this.voucherService = voucherService;
        this.orderNoteService = orderNoteService;
        this.valorService = valorService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.reservaContextService = reservaContextService;
        this.valorMovimientoService = valorMovimientoService;
        this.reservaArticuloService = reservaArticuloService;
        this.articuloMovimientoService = articuloMovimientoService;
        this.reservaService = reservaService;
        this.contabilidadService = contabilidadService;
    }

    @Transactional
    public ClienteMovimiento registraTransaccionFacturaProgramaDia(Reserva reserva, FacturacionDto facturacionDTO, Comprobante comprobante, Empresa empresa, Cliente cliente, Parametro parametro, ReservaContext reservaContext) {
        Voucher voucher = voucherService.findByVoucherId(reserva.getVoucherId());
        logVoucher(voucher);
        OrderNote orderNote = orderNoteService.findByOrderNumberId(Long.valueOf(Objects.requireNonNull(voucher.getNumeroVoucher())));
        logOrderNote(orderNote);
        int valorId = switch (Objects.requireNonNull(Objects.requireNonNull(orderNote.getPayment()).getMarcaTarjeta())) {
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
                    .centroStockId(Objects.requireNonNull(reservaArticulo.getArticulo()).getCentroStockId())
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

        List<CuentaMovimiento> clienteMovimientos = contabilidadService.registraContabilidadProgramaDia(clienteMovimiento, valorMovimiento, valor, articuloMovimientos, facturacionDTO, comprobante, parametro);

        reserva.setFacturada((byte) 1);
        reserva.setVerificada((byte) 1);
        reserva = reservaService.update(reserva, reserva.getReservaId());

        voucher.setConfirmado((byte) 1);
        voucher = voucherService.update(voucher, voucher.getVoucherId());

        return clienteMovimiento;

    }

    @Transactional
    public ClienteMovimiento registraTransaccionFacturaFaltante(ClienteMovimiento clienteMovimiento,
                                                                ArticuloMovimiento articuloMovimiento) {
        log.debug("Processing FacturacionService.registraTransaccionFacturaFaltante");
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
        logClienteMovimiento(clienteMovimiento);
        articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        articuloMovimiento = articuloMovimientoService.add(articuloMovimiento);
        logArticuloMovimiento(articuloMovimiento);
        contabilidadService.registraFacturaFaltanteCuentaCorriente(clienteMovimiento, articuloMovimiento);
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

    private void logOrderNote(OrderNote orderNote) {
        try {
            log.debug("orderNote={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(orderNote));
        } catch (JsonProcessingException e) {
            log.debug("orderNote jsonify error {}", e.getMessage());
        }
    }

    private void logVoucher(Voucher voucher) {
        try {
            log.debug("voucher={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(voucher));
        } catch (JsonProcessingException e) {
            log.debug("voucher jsonify error {}", e.getMessage());
        }
    }

    private void logClienteMovimiento(ClienteMovimiento clienteMovimiento) {
        try {
            log.debug("clienteMovimiento={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(clienteMovimiento));
        } catch (JsonProcessingException e) {
            log.debug("clienteMovimiento jsonify error {}", e.getMessage());
        }
    }

    private void logArticuloMovimiento(ArticuloMovimiento articuloMovimiento) {
        try {
            log.debug("articuloMovimiento={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(articuloMovimiento));
        } catch (JsonProcessingException e) {
            log.debug("articuloMovimiento jsonify error {}", e.getMessage());
        }
    }

}
