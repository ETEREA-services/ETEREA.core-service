package eterea.core.service.hexagonal.contable.cuentamovimiento.application.service;

import eterea.core.service.hexagonal.contable.cuentamovimiento.application.exception.CuentaMovimientoException;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.in.*;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.ports.out.CuentaMovimientoRepository;
import eterea.core.service.service.CuentaMovimientoAperturaService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CuentaMovimientoService {

    private final FindCuentaMovimientoByIdUseCase findCuentaMovimientoByIdUseCase;
    private final FindCuentaMovimientosByContableUseCase findCuentaMovimientosByContableUseCase;
    private final FindCuentaMovimientosByFechaRangeUseCase findCuentaMovimientosByFechaRangeUseCase;
    private final FindLastCuentaMovimientoByAsientoUseCase findLastCuentaMovimientoByAsientoUseCase;
    private final FindLastCuentaMovimientoByFechaUseCase findLastCuentaMovimientoByFechaUseCase;
    private final SaveCuentaMovimientosUseCase saveCuentaMovimientosUseCase;
    private final DeleteCuentaMovimientosByContableUseCase deleteCuentaMovimientosByContableUseCase;
    private final CuentaMovimientoRepository repository;
    private final CuentaMovimientoAperturaService cuentaMovimientoAperturaService;

    public CuentaMovimientoService(FindCuentaMovimientoByIdUseCase findCuentaMovimientoByIdUseCase,
                                   FindCuentaMovimientosByContableUseCase findCuentaMovimientosByContableUseCase,
                                   FindCuentaMovimientosByFechaRangeUseCase findCuentaMovimientosByFechaRangeUseCase,
                                   FindLastCuentaMovimientoByAsientoUseCase findLastCuentaMovimientoByAsientoUseCase,
                                   FindLastCuentaMovimientoByFechaUseCase findLastCuentaMovimientoByFechaUseCase,
                                   SaveCuentaMovimientosUseCase saveCuentaMovimientosUseCase,
                                   DeleteCuentaMovimientosByContableUseCase deleteCuentaMovimientosByContableUseCase,
                                   CuentaMovimientoRepository repository,
                                   CuentaMovimientoAperturaService cuentaMovimientoAperturaService) {
        this.findCuentaMovimientoByIdUseCase = findCuentaMovimientoByIdUseCase;
        this.findCuentaMovimientosByContableUseCase = findCuentaMovimientosByContableUseCase;
        this.findCuentaMovimientosByFechaRangeUseCase = findCuentaMovimientosByFechaRangeUseCase;
        this.findLastCuentaMovimientoByAsientoUseCase = findLastCuentaMovimientoByAsientoUseCase;
        this.findLastCuentaMovimientoByFechaUseCase = findLastCuentaMovimientoByFechaUseCase;
        this.saveCuentaMovimientosUseCase = saveCuentaMovimientosUseCase;
        this.deleteCuentaMovimientosByContableUseCase = deleteCuentaMovimientosByContableUseCase;
        this.repository = repository;
        this.cuentaMovimientoAperturaService = cuentaMovimientoAperturaService;
    }

    public List<CuentaMovimiento> findAllByFechaBetween(OffsetDateTime fechaDesde, OffsetDateTime fechaHasta) {
        return findCuentaMovimientosByFechaRangeUseCase.findAllByFechaBetween(fechaDesde, fechaHasta);
    }

    public List<CuentaMovimiento> findAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        return findCuentaMovimientosByContableUseCase.findAllByContable(fechaContable, ordenContable);
    }

    public CuentaMovimiento findByCuentaMovimientoId(Long cuentaMovimientoId) {
        return findCuentaMovimientoByIdUseCase.findByCuentaMovimientoId(cuentaMovimientoId);
    }

    public CuentaMovimiento findLastByAsiento(OffsetDateTime fecha, Integer orden) {
        return findLastCuentaMovimientoByAsientoUseCase.findLastByAsiento(fecha, orden);
    }

    @Transactional
    public List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos) {
        return saveCuentaMovimientosUseCase.saveAll(cuentaMovimientos);
    }

    public BigDecimal totalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
        if (incluyeApertura) {
            total = total.add(cuentaMovimientoAperturaService.calculateTotalDebeEntreFechas(numeroCuenta, desde, hasta));
        }
        total = total.add(calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(numeroCuenta, 1, incluyeInflacion, desde, hasta));
        return total;
    }

    public BigDecimal totalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
        if (incluyeApertura) {
            total = total.add(cuentaMovimientoAperturaService.calculateTotalHaberEntreFechas(numeroCuenta, desde, hasta));
        }
        total = total.add(calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(numeroCuenta, 0, incluyeInflacion, desde, hasta));
        return total;
    }

    private BigDecimal calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Boolean incluyeInflacion, OffsetDateTime desde, OffsetDateTime hasta) {
        if (incluyeInflacion) {
            return repository.calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(numeroCuenta, debita, desde, hasta);
        }
        return repository.calculateTotalByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(numeroCuenta, debita, 0, desde, hasta);
    }

    public List<BigDecimal> totalesEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        List<BigDecimal> totales = new ArrayList<>();
        totales.add(this.totalDebeEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion));
        totales.add(this.totalHaberEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion));
        return totales;
    }

    public int nextOrdenContable(OffsetDateTime fechaContable) {
        log.debug("Processing CuentaMovimientoService.nextOrdenContable");
        try {
            var lastByFecha = findLastCuentaMovimientoByFechaUseCase.findLastByFecha(fechaContable);
            var ordenContable = 1 + lastByFecha.getOrden();
            log.debug("OrdenContable -> {}", ordenContable);
            return ordenContable;
        } catch (CuentaMovimientoException e) {
            log.debug("sin asientos");
        }
        return 1;
    }

    @Transactional
    public void deleteAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        deleteCuentaMovimientosByContableUseCase.deleteAllByContable(fechaContable, ordenContable);
    }

}
