package eterea.core.service.service.facade;

import eterea.core.service.kotlin.exception.StockMovimientoException;
import eterea.core.service.kotlin.model.ArticuloCentro;
import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.ArticuloSaldoFecha;
import eterea.core.service.kotlin.model.StockMovimiento;
import eterea.core.service.kotlin.model.dto.StockAndArticulosDto;
import eterea.core.service.service.ArticuloCentroService;
import eterea.core.service.service.ArticuloMovimientoService;
import eterea.core.service.service.ArticuloSaldoFechaService;
import eterea.core.service.service.StockMovimientoService;
import eterea.core.service.service.view.SaldoArticuloService;
import eterea.core.service.service.view.SaldoFechaService;
import eterea.core.service.tool.Jsonifier;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StockService {

    private final StockMovimientoService stockMovimientoService;
    private final ArticuloMovimientoService articuloMovimientoService;
    private final SaldoFechaService saldoFechaService;
    private final SaldoArticuloService saldoArticuloService;
    private final ArticuloSaldoFechaService articuloSaldoFechaService;
    private final ArticuloCentroService articuloCentroService;

    @Transactional
    public StockMovimiento addMovimiento(StockAndArticulosDto stockAndArticulos) {
        log.debug("Processing StockService.addMovimiento");
        long lastNumeroComprobanteInterno = 0L;
        try {
            lastNumeroComprobanteInterno = stockMovimientoService.getLastByComprobanteId(Objects.requireNonNull(stockAndArticulos.getStockMovimiento()).getComprobanteId()).getNumeroComprobanteInterno();
        } catch (StockMovimientoException e) {
            log.debug("Error al obtener el ultimo numero de comprobante interno -> {}", e.getMessage());
        }
        var stockMovimientoTemporal = stockAndArticulos.getStockMovimiento();
        assert stockMovimientoTemporal != null;
        log.debug("stockMovimientoTemporal -> {}", stockMovimientoTemporal.jsonify());
        stockMovimientoTemporal.setNumeroComprobanteInterno(lastNumeroComprobanteInterno + 1);
        var stockMovimiento = stockMovimientoService.add(stockMovimientoTemporal);
        log.debug("stockMovimiento -> {}", stockMovimiento.jsonify());

        Objects.requireNonNull(stockAndArticulos.getArticuloMovimientos())
                .forEach(articuloMovimiento -> articuloMovimiento.setStockMovimientoId(stockMovimiento.getStockMovimientoId()));

        var articuloMovimientos = addArticuloMovimientos(stockMovimiento.getCentroStockIdDesde(), stockMovimiento.getFechaRegistro(), stockAndArticulos.getArticuloMovimientos());
        log.debug("ArticuloMovimientos -> {}", Jsonifier.builder(articuloMovimientos).build());

        return stockMovimiento;
    }

    @Transactional
    public List<ArticuloMovimiento> addArticuloMovimientos(Integer centroStockId, OffsetDateTime fechaRegistro, List<ArticuloMovimiento> articuloMovimientos) {
        log.debug("Processing StockService.addArticuloMovimientos");
        articuloMovimientos = articuloMovimientoService.saveAll(articuloMovimientos);

        List<String> articuloIds = articuloMovimientos.stream()
                .filter(articuloMovimiento -> Objects.requireNonNull(articuloMovimiento.getArticulo()).getControlaStock() > 0)
                .map(ArticuloMovimiento::getArticuloId)
                .toList();

        calculateSaldos(centroStockId, fechaRegistro, articuloIds);

        return articuloMovimientos;
    }

    @Transactional
    protected void calculateSaldos(Integer centroStockId, OffsetDateTime fechaMovimiento, List<String> articuloIds) {
        log.debug("Processing StockService.calculateSaldos");
        Map<String, ArticuloSaldoFecha> articuloSaldoFechas = articuloSaldoFechaService.findAllByArticulos(centroStockId, fechaMovimiento, articuloIds).stream().collect(Collectors.toMap(ArticuloSaldoFecha::getArticuloId, saldo -> saldo));

        List<ArticuloSaldoFecha> nuevosPorFecha = saldoFechaService.findAllByArticulos(centroStockId, fechaMovimiento, articuloIds).stream()
                .map(saldoFecha -> {
                    Long articuloSaldoFechaId = articuloSaldoFechas.containsKey(saldoFecha.getArticuloId())
                            ? articuloSaldoFechas.get(saldoFecha.getArticuloId()).getArticuloSaldoFechaId()
                            : null;

                    return new ArticuloSaldoFecha.Builder()
                            .articuloSaldoFechaId(articuloSaldoFechaId)
                            .centroStockId(centroStockId)
                            .articuloId(saldoFecha.getArticuloId())
                            .fecha(fechaMovimiento)
                            .saldo(saldoFecha.getCantidad())
                            .build();
                })
                .collect(Collectors.toList());

        log.debug("grabando nuevos por fecha");
        articuloSaldoFechaService.saveAll(nuevosPorFecha);

        Map<String, ArticuloCentro> articuloCentros = articuloCentroService.findAllByArticulos(centroStockId, articuloIds).stream().collect(Collectors.toMap(ArticuloCentro::getArticuloId, saldo -> saldo));

        List<ArticuloCentro> nuevosPorArticulo = saldoArticuloService.findAllByArticulos(centroStockId, articuloIds).stream()
                .map(saldoArticulo -> {
                    Long articuloCentroId = articuloCentros.containsKey(saldoArticulo.getArticuloId())
                            ? articuloCentros.get(saldoArticulo.getArticuloId()).getArticuloCentroId()
                            : null;

                    return new ArticuloCentro.Builder()
                            .articuloCentroId(articuloCentroId)
                            .centroStockId(centroStockId)
                            .articuloId(saldoArticulo.getArticuloId())
                            .saldo(saldoArticulo.getCantidad())
                            .build();
                })
                .collect(Collectors.toList());

        log.debug("grabando nuevos por articulo");
        articuloCentroService.saveAll(nuevosPorArticulo);
    }

}
