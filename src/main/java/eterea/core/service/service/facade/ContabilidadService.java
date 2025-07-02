package eterea.core.service.service.facade;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.model.*;
import eterea.core.service.kotlin.model.dto.FacturacionDto;
import eterea.core.service.model.Track;
import eterea.core.service.service.*;
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
public class ContabilidadService {

    private final CuentaMovimientoService cuentaMovimientoService;
    private final ClienteMovimientoService clienteMovimientoService;
    private final ValorMovimientoService valorMovimientoService;
    private final ParametroService parametroService;
    private final ComprobanteService comprobanteService;
    private final TrackService trackService;

    public ContabilidadService(CuentaMovimientoService cuentaMovimientoService, ClienteMovimientoService clienteMovimientoService, ValorMovimientoService valorMovimientoService, ParametroService parametroService, ComprobanteService comprobanteService, TrackService trackService) {
        this.cuentaMovimientoService = cuentaMovimientoService;
        this.clienteMovimientoService = clienteMovimientoService;
        this.valorMovimientoService = valorMovimientoService;
        this.parametroService = parametroService;
        this.comprobanteService = comprobanteService;
        this.trackService = trackService;
    }

    public List<CuentaMovimiento> registraContabilidadProgramaDia(ClienteMovimiento clienteMovimiento, ValorMovimiento valorMovimiento, Valor valor, List<ArticuloMovimiento> articuloMovimientos, FacturacionDto facturacionDTO, Comprobante comprobante, Parametro parametro, Track track) {
        if (track == null) {
            track = trackService.startTracking("registra-contabilidad-programa-dia");
        }
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
                .trackUuid(track.getUuid())
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
                    .trackUuid(track.getUuid())
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
                    .trackUuid(track.getUuid())
                    .build());
        }
        // Registro de artículos
        for (ArticuloMovimiento articuloMovimiento : articuloMovimientos) {
            assert articuloMovimiento.getArticuloMovimientoId() != null;
            cuentaMovimientos.add(new CuentaMovimiento.Builder()
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
        }

        cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
        logCuentaMovimientos(cuentaMovimientos);
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
        logClienteMovimiento(clienteMovimiento);
        int item = 1;
        String concepto = String.format("Nro: %04d %06d", clienteMovimiento.getPuntoVenta(), clienteMovimiento.getNumeroComprobante());
        // Registro total deudores por ventas
        cuentaMovimientos.add(new CuentaMovimiento.Builder()
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
            cuentaMovimientos.add(new CuentaMovimiento.Builder()
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
            cuentaMovimientos.add(new CuentaMovimiento.Builder()
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
        cuentaMovimientos.add(new CuentaMovimiento.Builder()
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
        logCuentaMovimientos(cuentaMovimientos);
    }

    private void logCuentaMovimientos(List<CuentaMovimiento> cuentaMovimientos) {
        log.debug("Processing ContabilidadService.logCuentaMovimientos");
        try {
            log.debug("cuentaMovimientos={}", JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(cuentaMovimientos));
        } catch (JsonProcessingException e) {
            log.debug("cuentaMovimientos jsonify error {}", e.getMessage());
        }
    }

    private void logClienteMovimiento(ClienteMovimiento clienteMovimiento) {
        log.debug("Processing ContabilidadService.logClienteMovimiento");
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

}
