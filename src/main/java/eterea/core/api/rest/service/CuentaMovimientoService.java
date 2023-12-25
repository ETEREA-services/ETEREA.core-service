/**
 *
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.CuentaMovimientoException;
import eterea.core.api.rest.repository.ICuentaMovimientoRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.CuentaMovimiento;
import eterea.core.api.rest.model.dto.CuentaMovimientoDTO;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author daniel
 *
 */
@Service
public class CuentaMovimientoService {

    private final ICuentaMovimientoRepository repository;

    @Autowired
    public CuentaMovimientoService(ICuentaMovimientoRepository repository) {
        this.repository = repository;
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

    @Transactional
    public List<CuentaMovimiento> saveAll(List<CuentaMovimiento> cuentaMovimientos) {
        return repository.saveAll(cuentaMovimientos);
    }

    public BigDecimal totalDebeEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
//		if (incluyeApertura) {
//			total = total.add(cuentaMovimientoAperturaDiaService.totalDebeEntreFechas(numeroCuenta, desde, hasta));
//		}
        total = total.add(repository.calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(numeroCuenta, 1, incluyeInflacion, desde, hasta));
        return total;
    }

    public BigDecimal totalHaberEntreFechas(Long numeroCuenta, OffsetDateTime desde, OffsetDateTime hasta, Boolean incluyeApertura, Boolean incluyeInflacion) {
        BigDecimal total = BigDecimal.ZERO;
//		if (incluyeApertura) {
//			total = total.add(cuentaMovimientoAperturaDiaService.totalHaberEntreFechas(numeroCuenta, desde, hasta));
//		}
        total = total.add(repository.calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(numeroCuenta, 0, incluyeInflacion, desde, hasta));
        return total;
    }

}
