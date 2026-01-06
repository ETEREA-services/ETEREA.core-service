package eterea.core.service.service.facade;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.kotlin.extern.OrderNote;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.model.CuentaMovimiento;
import eterea.core.service.model.ReservaContext;
import eterea.core.service.model.Track;
import eterea.core.service.hexagonal.facturacion.arca.nacional.infrastructure.web.dto.FacturacionDto;
import eterea.core.service.service.ArticuloMovimientoService;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ReservaContextService;
import eterea.core.service.service.ValorMovimientoService;
import eterea.core.service.tool.ToolService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class RegistraFacturaService {

    private final ClienteMovimientoService clienteMovimientoService;
    private final ValorMovimientoService valorMovimientoService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final ContabilidadService contabilidadService;
    private final ReservaContextService reservaContextService;

    @Transactional
    public ClienteMovimiento registraFacturaCompleta(
                                Empresa empresa,
                                Cliente cliente,
                                Comprobante comprobante,
                                OffsetDateTime fechaComprobante,
                                FacturacionDto facturacionDto,
                                Reserva reserva,
                                String observaciones,
                                Track track,
                                Valor valor,
                                List<ReservaArticulo> reservaArticulos,
                                Parametro parametro
    ) {
        log.debug("Processing RegistraFacturaService.registraFacturaCompleta");
        ClienteMovimiento clienteMovimiento = ClienteMovimiento.builder()
                .negocioId(empresa.getNegocioId())
                .empresaId(empresa.getEmpresaId())
                .clienteId(cliente.getClienteId())
                .comprobanteId(comprobante.getComprobanteId())
                .fechaComprobante(fechaComprobante)
                .fechaVencimiento(fechaComprobante)
                .importe(facturacionDto.getTotal())
                .cancelado(facturacionDto.getTotal())  // contado
                .puntoVenta(comprobante.getPuntoVenta())
                .numeroComprobante(facturacionDto.getNumeroComprobante())
                .montoIva(facturacionDto.getIva())
                .montoIvaRni(facturacionDto.getIva105())
                .neto(facturacionDto.getNeto().add(facturacionDto.getNeto105()))
                .letraComprobante(comprobante.getLetraComprobante())
                .montoExento(facturacionDto.getExento())
                .reservaId(reserva.getReservaId())
                .cae(facturacionDto.getCae())
                .caeVencimiento(facturacionDto.getVencimientoCae())
                .monedaId(1)
                .cotizacion(BigDecimal.ONE)
                .letras(ToolService.number_2_text(facturacionDto.getTotal()))
                .observaciones(observaciones)
                .trackUuid(track.getUuid())
                .build();
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());

        ValorMovimiento valorMovimiento = new ValorMovimiento.Builder()
                .negocioId(empresa.getNegocioId())
                .clienteId(cliente.getClienteId())
                .proveedorId(0L)
                .comprobanteId(comprobante.getComprobanteId())
                .fechaEmision(clienteMovimiento.getFechaComprobante())
                .fechaVencimiento(clienteMovimiento.getFechaComprobante())
                .valorId(valor.getValorId())
                .numeroComprobante(0L)
                .importe(facturacionDto.getTotal())
                .numeroCuenta(valor.getNumeroCuenta())
                .proveedorMovimientoId(0L)
                .titular("")
                .banco("")
                .receptor("")
                .estadoId(0)
                .cierreCajaId(0L)
                .observaciones(observaciones)
                .trackUuid(track.getUuid())
                .build();
        log.debug("ValorMovimiento -> {}", valorMovimiento.jsonify());

        int item = 1;
        List<ArticuloMovimiento> articuloMovimientos = new ArrayList<>();
        for (ReservaArticulo reservaArticulo : reservaArticulos) {
            articuloMovimientos.add(new ArticuloMovimiento.Builder()
                    .centroStockId(Objects.requireNonNull(reservaArticulo.getArticulo()).getCentroStockId())
                    .comprobanteId(comprobante.getComprobanteId())
                    .item(item++)
                    .articuloId(reservaArticulo.getArticuloId())
                    .negocioId(clienteMovimiento.getNegocioId())
                    .cantidad(new BigDecimal(-1 * reservaArticulo.getCantidad()))
                    .precioUnitario(reservaArticulo.getPrecioUnitario())
                    .precioUnitarioSinIva(
                            calcularPrecioSinIva(
                                    reservaArticulo.getPrecioUnitario(),
                                    reservaArticulo.getArticulo().getIva105(),
                                    reservaArticulo.getArticulo().getExento(),
                                    parametro
                            )
                    )
                    .precioUnitarioConIva(reservaArticulo.getPrecioUnitario())
                    .numeroCuenta(reservaArticulo.getArticulo().getCuentaVentas())
                    .iva105(reservaArticulo.getArticulo().getIva105())
                    .exento(reservaArticulo.getArticulo().getExento())
                    .fechaMovimiento(clienteMovimiento.getFechaComprobante())
                    .fechaFactura(clienteMovimiento.getFechaComprobante())
                    .precioCompra(reservaArticulo.getArticulo().getPrecioCompra())
                    .trackUuid(track.getUuid())
                    .build());
            log.debug("ArticuloMovimiento -> {}", articuloMovimientos.getLast().jsonify());
        }

        // Comienza registro en la db
        // Registra clienteMovimiento
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());

        // Registra valorMovimiento
        valorMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        valorMovimiento = valorMovimientoService.add(valorMovimiento);
        log.debug("ValorMovimiento -> {}", valorMovimiento.jsonify());

        // Registra articuloMovimientos
        for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
            articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
            log.debug("ArticuloMovimiento para registrar -> {}", articuloMovimiento.jsonify());
        }
        articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

        List<CuentaMovimiento> cuentaMovimientos = contabilidadService.registraContabilidadProgramaDia(
                clienteMovimiento,
                valorMovimiento,
                valor,
                articuloMovimientos,
                facturacionDto,
                comprobante,
                parametro,
                track
        );
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
    public ReservaContext markReservaContextFacturada(ReservaContext reservaContext, OrderNote orderNote, FacturacionDto facturacionDto) {
        log.debug("Processing RegistraFacturaService.markReservaContextFacturada");
        log.debug("FacturacionDto -> {}", facturacionDto.jsonify());
        reservaContext.setFacturaPendiente((byte) 0);
        reservaContext.setDiferenciaWeb(orderNote.getOrderTotal().subtract(facturacionDto.getTotal()).setScale(2, RoundingMode.HALF_UP));
        reservaContext.setFacturaArca((byte) 1);
        reservaContext.setPayloadArca(facturacionDto.jsonify());
        reservaContext = reservaContextService.update(reservaContext, reservaContext.getReservaContextId());
        log.debug("ReservaContext -> {}", reservaContext.jsonify());
        return reservaContext;
    }

}
