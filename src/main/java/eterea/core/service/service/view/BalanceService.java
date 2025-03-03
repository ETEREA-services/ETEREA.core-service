package eterea.core.service.service.view;

import eterea.core.service.kotlin.model.CuentaMovimiento;
import eterea.core.service.kotlin.model.CuentaMovimientoMoneda;
import eterea.core.service.kotlin.model.MonedaCotizacion;
import eterea.core.service.kotlin.model.view.AsientoView;
import eterea.core.service.service.CuentaMovimientoAperturaMonedaService;
import eterea.core.service.service.CuentaMovimientoMonedaService;
import eterea.core.service.service.CuentaMovimientoService;
import eterea.core.service.service.MonedaCotizacionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BalanceService {

    private final AsientoViewService asientoViewService;

    private final CuentaMovimientoService cuentaMovimientoService;
    private final MonedaCotizacionService monedaCotizacionService;
    private final CuentaMovimientoMonedaService cuentaMovimientoMonedaService;

    private static final int BATCH_SIZE = 1000;
    private static final int SCALE = 2;
    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
    private final CuentaMovimientoAperturaMonedaService cuentaMovimientoAperturaMonedaService;

    public BalanceService(AsientoViewService asientoViewService, CuentaMovimientoService cuentaMovimientoService, MonedaCotizacionService monedaCotizacionService, CuentaMovimientoMonedaService cuentaMovimientoMonedaService, CuentaMovimientoAperturaMonedaService cuentaMovimientoAperturaMonedaService) {
        this.asientoViewService = asientoViewService;
        this.cuentaMovimientoService = cuentaMovimientoService;
        this.monedaCotizacionService = monedaCotizacionService;
        this.cuentaMovimientoMonedaService = cuentaMovimientoMonedaService;
        this.cuentaMovimientoAperturaMonedaService = cuentaMovimientoAperturaMonedaService;
    }

    public void findAllDifferences() {
        List<AsientoView> asientos = asientoViewService.findAllDifferences(OffsetDateTime.of(2022, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), OffsetDateTime.of(2022, 12, 31, 23, 59, 59, 0, ZoneOffset.UTC));
        List<CuentaMovimiento> cuentaMovimientos = new ArrayList<>();
        for (AsientoView asientoView : asientos) {
            BigDecimal diferencia = asientoView.getDebe().subtract(asientoView.getHaber()).setScale(2, RoundingMode.HALF_UP);
            byte debita = (byte) (diferencia.compareTo(BigDecimal.ZERO) < 0 ? 0 : 1);
            Long numeroCuenta = 44106000L;
            CuentaMovimiento lastByAsiento = cuentaMovimientoService.findLastByAsiento(asientoView.getFecha(), asientoView.getOrden());
            CuentaMovimiento cuentaMovimiento = new CuentaMovimiento(null, lastByAsiento.getFecha(), lastByAsiento.getOrden(), 1 + lastByAsiento.getItem(), debita, lastByAsiento.getNegocioId(), numeroCuenta, lastByAsiento.getComprobanteId(), lastByAsiento.getConcepto(), diferencia.abs(), lastByAsiento.getSubrubroId(), lastByAsiento.getProveedorId(), lastByAsiento.getClienteId(), lastByAsiento.getCierreCajaId(), lastByAsiento.getNivel(), lastByAsiento.getFirma(), lastByAsiento.getTipoAsientoId(), lastByAsiento.getArticuloMovimientoId(), lastByAsiento.getEjercicioId(), lastByAsiento.getInflacion(), null, null, null);
            cuentaMovimientos.add(cuentaMovimiento);
        }
        cuentaMovimientos = cuentaMovimientoService.saveAll(cuentaMovimientos);
    }

    public List<AsientoView> findAsientosByFecha(Integer negocioId, OffsetDateTime fecha) {
        return asientoViewService.findAllByNegocioIdAndFecha(negocioId, fecha);
    }

    @Transactional
    public void generateMovimientosCotizados(Integer monedaIdOrigen, Integer monedaIdDestino, OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        log.debug("Iniciando generación de movimientos cotizados para moneda origen {} destino {} entre {} y {}",
                monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta);
                
        monedaCotizacionService.fillCotizacion(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta);
        cuentaMovimientoMonedaService.generateMovimientosCotizados(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta);
        cuentaMovimientoAperturaMonedaService.generateMovimientosCotizados(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta);
        
        // Limpiar registros huérfanos después de la generación
        int deletedCount = cuentaMovimientoMonedaService.deleteOrphanedRecords(monedaIdDestino, fechaDesde, fechaHasta);
        log.debug("Se eliminaron {} registros huérfanos", deletedCount);
        deletedCount = cuentaMovimientoAperturaMonedaService.deleteOrphanedRecords(monedaIdOrigen, fechaDesde, fechaHasta);
        log.debug("Se eliminaron {} registros huérfanos", deletedCount);
    }

}
