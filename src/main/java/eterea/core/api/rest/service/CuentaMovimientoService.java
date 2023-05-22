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

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author daniel
 *
 */
@Service
public class CuentaMovimientoService {

	@Autowired
	private ICuentaMovimientoRepository repository;

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
}
