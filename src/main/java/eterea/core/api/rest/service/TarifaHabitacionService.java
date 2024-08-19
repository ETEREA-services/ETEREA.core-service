/**
 * 
 */
package eterea.core.api.rest.service;

import java.math.BigDecimal;
import java.util.List;

import eterea.core.api.rest.kotlin.model.TarifaHabitacion;
import eterea.core.api.rest.repository.TarifaHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class TarifaHabitacionService {

	@Autowired
	private TarifaHabitacionRepository repository;

	public List<TarifaHabitacion> findAllSinBloqueo(Boolean bloqueoEspecial) {
		if (!bloqueoEspecial) {
			return repository.findAllByBloqueoFacturaAndPrecioOrderByDescripcion((byte) 0, BigDecimal.ZERO);
		}
		return repository.findAllByBloqueoFacturaOrderByDescripcion((byte) 0);
	}

}
