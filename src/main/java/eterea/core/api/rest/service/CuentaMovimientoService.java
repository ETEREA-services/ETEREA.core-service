/**
 *
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.CuentaMovimientoException;
import eterea.core.api.rest.kotlin.model.CuentaMovimiento;
import eterea.core.api.rest.kotlin.repository.CuentaMovimientoRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.dto.CuentaMovimientoDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daniel
 */
@Service
@Slf4j
public class CuentaMovimientoService {

    private final CuentaMovimientoRepository repository;

    private final CuentaMovimientoAperturaService cuentaMovimientoAperturaService;

    @Autowired
    public CuentaMovimientoService(CuentaMovimientoRepository repository, CuentaMovimientoAperturaService cuentaMovimientoAperturaService) {
        this.repository = repository;
        this.cuentaMovimientoAperturaService = cuentaMovimientoAperturaService;
    }

    public CuentaMovimientoDTO findByCuentaMovimientoId(Long cuentaMovimientoId) {
        CuentaMovimiento cuentaMovimiento = repository.findByCuentaMovimientoId(cuentaMovimientoId)
                .orElseThrow(() -> new CuentaMovimientoException(cuentaMovimientoId));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cuentaMovimiento, CuentaMovimientoDTO.class);
    }

    public CuentaMovimientoDTO findLastByAsiento(OffsetDateTime fecha, Integer orden) {
        CuentaMovimiento cuentaMovimiento = repository.findFirstByFechaAndOrdenOrderByItemDesc(fecha, orden).orElseThrow(() -> new CuentaMovimientoException(fecha, orden));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(cuentaMovimiento, CuentaMovimientoDTO.class);
    }

    private CuentaMovimiento findLastByFecha(OffsetDateTime fecha) {
        return repository.findFirstByFechaOrderByOrdenDesc(fecha).orElseThrow(() -> new CuentaMovimientoException(fecha));
    }

    @Transactional
    public List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos) {
        return repository.saveAll(cuentaMovimientos);
    }

    public BigDecimal totalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
        if (incluyeApertura) {
            total = total.add(cuentaMovimientoAperturaService.calculateTotalDebeEntreFechas(numeroCuenta, desde, hasta));
        }
        total = total.add(repository.calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(numeroCuenta, 1, incluyeInflacion, desde, hasta));
        return total;
    }

    public BigDecimal totalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
        if (incluyeApertura) {
            total = total.add(cuentaMovimientoAperturaService.calculateTotalHaberEntreFechas(numeroCuenta, desde, hasta));
        }
        total = total.add(repository.calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(numeroCuenta, 0, incluyeInflacion, desde, hasta));
        return total;
    }

    public List<BigDecimal> totalesEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        List<BigDecimal> totales = new ArrayList<>();
        // debe
        totales.add(this.totalDebeEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion));
        // haber
        totales.add(this.totalHaberEntreFechas(numeroCuenta, desde, hasta, incluyeApertura, incluyeInflacion));
        return totales;
    }

    public int nextOrdenContable(OffsetDateTime fechaContable) {

        try {
            return 1 + findLastByFecha(fechaContable).getOrden();
        } catch (CuentaMovimientoException e) {
            log.debug("sin asientos");
        }
        return 1;
    }

}
