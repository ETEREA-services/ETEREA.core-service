/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.HabitacionException;
import eterea.core.service.kotlin.model.Habitacion;
import eterea.core.service.repository.HabitacionRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionService {

	private final HabitacionRepository repository;

	public HabitacionService(HabitacionRepository repository) {
		this.repository = repository;
	}

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
