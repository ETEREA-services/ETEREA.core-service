package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.Track;
import eterea.core.service.model.dto.FacturacionDto;
import eterea.core.service.service.ArticuloMovimientoService;
import eterea.core.service.service.ClienteMovimientoService;
import eterea.core.service.service.ValorMovimientoService;
import eterea.core.service.tool.ToolService;
import jakarta.transaction.Transactional;
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
public class RegistraFacturaService {

    private final ClienteMovimientoService clienteMovimientoService;
    private final ValorMovimientoService valorMovimientoService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final ContabilidadService contabilidadService;

    public RegistraFacturaService(ClienteMovimientoService clienteMovimientoService, ValorMovimientoService valorMovimientoService, ArticuloMovimientoService articuloMovimientoService, ContabilidadService contabilidadService) {
        this.clienteMovimientoService = clienteMovimientoService;
        this.valorMovimientoService = valorMovimientoService;
        this.articuloMovimientoService = articuloMovimientoService;
        this.contabilidadService = contabilidadService;
    }

    @Transactional
    public ClienteMovimiento registraFacturaCompleta(Empresa empresa,
                                Cliente cliente,
                                Comprobante comprobante,
                                OffsetDateTime fechaComprobante,
                                FacturacionDto facturacionDto,
                                Reserva reserva,
                                String observaciones,
                                Track track,
                                Valor valor,
                                List<ReservaArticulo> reservaArticulos,
                                Parametro parametro) {
        ClienteMovimiento clienteMovimiento = new ClienteMovimiento.Builder()
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
                .neto(facturacionDto.getNeto())
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
        }

        // Comienza registro en la db
        // Registra clienteMovimiento
        clienteMovimiento = clienteMovimientoService.add(clienteMovimiento);
        logClienteMovimiento(clienteMovimiento);

        // Registra valorMovimiento
        valorMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
        valorMovimiento = valorMovimientoService.add(valorMovimiento);
        logValorMovimiento(valorMovimiento);

        // Registra articuloMovimientos
        for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
            articuloMovimiento.setClienteMovimientoId(clienteMovimiento.getClienteMovimientoId());
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

    private void logValorMovimiento(ValorMovimiento valorMovimiento) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(valorMovimiento);
            log.debug("valorMovimiento={}", json);
        } catch (JsonProcessingException e) {
            log.debug("valorMovimiento jsonify error {}", e.getMessage());
        }
    }

    private void logClienteMovimiento(ClienteMovimiento clienteMovimiento) {
        try {
            var json = JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(clienteMovimiento);
            log.debug("clienteMovimiento={}", json);
        } catch (JsonProcessingException e) {
            log.debug("clienteMovimiento jsonify error {}", e.getMessage());
        }
    }

}
