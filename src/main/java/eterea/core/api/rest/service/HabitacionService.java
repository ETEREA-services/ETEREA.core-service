/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.HabitacionException;
import eterea.core.api.rest.kotlin.model.Habitacion;
import eterea.core.api.rest.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionService {

	@Autowired
	private HabitacionRepository repository;

	public Habitacion findByNumero(Integer numero) {
		return repository.findByNumero(numero).orElseThrow(() -> new HabitacionException(numero));
	}

	public Habitacion update(Habitacion newHabitacion, Integer numero) {
		return repository.findByNumero(numero).map(habitacion -> {
			habitacion = new Habitacion(numero, newHabitacion.getHabitacionTipoId(), newHabitacion.getClienteId());
			habitacion = repository.save(habitacion);
			return habitacion;
		}).orElseThrow(() -> new HabitacionException(numero));
	}

}
