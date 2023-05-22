/**
 * 
 */
package eterea.core.api.rest.service;

import java.math.BigDecimal;
import java.util.List;

import eterea.core.api.rest.repository.ITarifaHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.TarifaHabitacion;

/**
 * @author daniel
 *
 */
@Service
public class TarifaHabitacionService {

	@Autowired
	private ITarifaHabitacionRepository repository;

	public List<TarifaHabitacion> findAllSinBloqueo(Boolean bloqueoEspecial) {
		if (!bloqueoEspecial) {
			return repository.findAllByBloqueoFacturaAndPrecioOrderByDescripcion((byte) 0, BigDecimal.ZERO);
		}
		return repository.findAllByBloqueoFacturaOrderByDescripcion((byte) 0);
	}

}
