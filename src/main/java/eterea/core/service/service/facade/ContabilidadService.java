package eterea.core.service.service.facade;

import eterea.core.service.kotlin.model.*;
import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.model.CuentaMovimiento;
import eterea.core.service.model.Track;
import eterea.core.service.hexagonal.facturacion.arca.nacional.infrastructure.web.dto.FacturacionDto;
import eterea.core.service.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ContabilidadService {

    private final CuentaMovimientoService cuentaMovimientoService;
    private final ClienteMovimientoService clienteMovimientoService;
    private final ValorMovimientoService valorMovimientoService;
    private final ParametroService parametroService;
    private final ComprobanteService comprobanteService;
    private final TrackService trackService;

    public List<CuentaMovimiento> registraContabilidadProgramaDia(
            ClienteMovimiento clienteMovimiento,
            ValorMovimiento valorMovimiento,
            Valor valor,
            List<ArticuloMovimiento> articuloMovimientos,
            FacturacionDto facturacionDto,
            Comprobante comprobante,
            Parametro parametro,
            Track track
    ) {
        log.debug("Processing ContabilidadService.registraContabilidadProgramaDia");
        if (track == null) {
            track = trackService.startTracking("registra-contabilidad-programa-dia");
        }
        List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
        int ordenContable = cuentaMovimientoService.nextOrdenContable(clienteMovimiento.getFechaComprobante());
        // Agrego asiento contable a clienteMovimiento
        clienteMovimiento.setFechaContable(clienteMovimiento.getFechaComprobante());
        clienteMovimiento.setOrdenContable(ordenContable);
        clienteMovimiento = clienteMovimientoService.update(clienteMovimiento, clienteMovimiento.getClienteMovimientoId());
        log.debug("ClienteMovimiento -> {}", clienteMovimiento.jsonify());
        // Agrego asiento contable a valorMovimiento
        valorMovimiento.setFechaContable(clienteMovimiento.getFechaContable());
        valorMovimiento.setOrdenContable(ordenContable);
        valorMovimiento = valorMovimientoService.update(valorMovimiento, valorMovimiento.getValorMovimientoId());
        log.debug("ValorMovimiento -> {}", valorMovimiento.jsonify());
        int item = 1;
        String concepto = String.format("Nro: %04d %06d", facturacionDto.getPuntoVenta(), facturacionDto.getNumeroComprobante());
        // Registro total valores
        cuentaMovimientos.add(CuentaMovimiento.builder()
                .negocioId(clienteMovimiento.getNegocioId())
                .numeroCuenta(valor.getNumeroCuenta())
                .debita(comprobante.getDebita())
                .importe(facturacionDto.getTotal())
                .item(item++)
                .fecha(clienteMovimiento.getFechaComprobante())
                .comprobanteId(comprobante.getComprobanteId())
                .orden(ordenContable)
                .clienteId(clienteMovimiento.getClienteId())
                .subrubroId(2L)
                .concepto(concepto)
                .trackUuid(track.getUuid())
                .build());
        log.debug("CuentaMovimiento -> {}", cuentaMovimientos.getLast().jsonify());
        // Registro iva 21
        if (facturacionDto.getIva().compareTo(BigDecimal.ZERO) > 0) {
            cuentaMovimientos.add(CuentaMovimiento.builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(parametro.getCuentaIvaVentas())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(facturacionDto.getIva())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .trackUuid(track.getUuid())
                    .build());
            log.debug("CuentaMovimiento -> {}", cuentaMovimientos.getLast().jsonify());
        }
        // Registro iva 10.5
        if (facturacionDto.getIva105().compareTo(BigDecimal.ZERO) > 0) {
            cuentaMovimientos.add(CuentaMovimiento.builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(parametro.getCuentaIvaRniVentas())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(facturacionDto.getIva105())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .trackUuid(track.getUuid())
                    .build());
            log.debug("CuentaMovimiento -> {}", cuentaMovimientos.getLast().jsonify());
        }
        // Registro de artículos
        for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
            assert articuloMovimiento.getArticuloMovimientoId() != null;
            cuentaMovimientos.add(CuentaMovimiento.builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(articuloMovimiento.getNumeroCuenta())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(Objects.requireNonNull(articuloMovimiento.getPrecioUnitarioSinIva()).multiply(articuloMovimiento.getCantidad()).setScale(2, RoundingMode.HALF_UP).abs())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .trackUuid(track.getUuid())
                    .articuloMovimientoId(articuloMovimiento.getArticuloMovimientoId())
                    .build());
            log.debug("CuentaMovimiento -> {}", cuentaMovimientos.getLast().jsonify());
        }

        cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
        return cuentaMovimientos;
    }

    public void registraFacturaFaltanteCuentaCorriente(ClienteMovimiento clienteMovimiento, ArticuloMovimiento articuloMovimiento) {
        log.debug("Processing ContabilidadService.registraFacturaFaltanteCuentaCorriente");
        var parametro = parametroService.findTop();
        var comprobante = comprobanteService.findByComprobanteId(clienteMovimiento.getComprobanteId());
        List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
        int ordenContable = cuentaMovimientoService.nextOrdenContable(clienteMovimiento.getFechaComprobante());
        // Agrego asiento contable a clienteMovimiento
        clienteMovimiento.setFechaContable(clienteMovimiento.getFechaComprobante());
        clienteMovimiento.setOrdenContable(ordenContable);
        clienteMovimiento = clienteMovimientoService.update(clienteMovimiento, clienteMovimiento.getClienteMovimientoId());
        log.debug("ClienteMovimiento -> {}", clienteMovimiento);
        int item = 1;
        String concepto = String.format("Nro: %04d %06d", clienteMovimiento.getPuntoVenta(), clienteMovimiento.getNumeroComprobante());
        // Registro total deudores por ventas
        cuentaMovimientos.add(CuentaMovimiento.builder()
                .negocioId(clienteMovimiento.getNegocioId())
                .numeroCuenta(12101001L)
                .debita((byte) 1)
                .importe(clienteMovimiento.getImporte())
                .item(item++)
                .fecha(clienteMovimiento.getFechaComprobante())
                .comprobanteId(clienteMovimiento.getComprobanteId())
                .orden(ordenContable)
                .clienteId(clienteMovimiento.getClienteId())
                .subrubroId(2L)
                .concepto(concepto)
                .build());
        // Registro iva 21
        if (clienteMovimiento.getMontoIva().compareTo(BigDecimal.ZERO) > 0) {
            cuentaMovimientos.add(CuentaMovimiento.builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(parametro.getCuentaIvaVentas())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(clienteMovimiento.getMontoIva())
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
        if (clienteMovimiento.getMontoIvaRni().compareTo(BigDecimal.ZERO) > 0) {
            cuentaMovimientos.add(CuentaMovimiento.builder()
                    .negocioId(clienteMovimiento.getNegocioId())
                    .numeroCuenta(parametro.getCuentaIvaRniVentas())
                    .debita((byte) (1 - comprobante.getDebita()))
                    .importe(clienteMovimiento.getMontoIvaRni())
                    .item(item++)
                    .fecha(clienteMovimiento.getFechaComprobante())
                    .comprobanteId(comprobante.getComprobanteId())
                    .orden(ordenContable)
                    .clienteId(clienteMovimiento.getClienteId())
                    .subrubroId(2L)
                    .concepto(concepto)
                    .build());
        }
        // Registro de artículo
        assert articuloMovimiento.getArticuloMovimientoId() != null;
        cuentaMovimientos.add(CuentaMovimiento.builder()
                .negocioId(clienteMovimiento.getNegocioId())
                .numeroCuenta(articuloMovimiento.getNumeroCuenta())
                .debita((byte) (1 - comprobante.getDebita()))
                .importe(clienteMovimiento.getNeto().abs())
                .item(item++)
                .fecha(clienteMovimiento.getFechaComprobante())
                .comprobanteId(comprobante.getComprobanteId())
                .orden(ordenContable)
                .clienteId(clienteMovimiento.getClienteId())
                .subrubroId(2L)
                .concepto(concepto)
                .articuloMovimientoId(articuloMovimiento.getArticuloMovimientoId())
                .build());

        cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
    }

}
