/**
 * 
 */
package eterea.api.rest.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.CuentaMovimientoException;
import eterea.api.rest.model.CuentaMovimiento;
import eterea.api.rest.model.dto.CuentaMovimientoDTO;
import eterea.api.rest.repository.ICuentaMovimientoRepository;

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

}
