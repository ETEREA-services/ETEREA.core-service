/**
 * 
 */
package eterea.api.rest.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.TarifaHabitacion;
import eterea.api.rest.repository.ITarifaHabitacionRepository;

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
