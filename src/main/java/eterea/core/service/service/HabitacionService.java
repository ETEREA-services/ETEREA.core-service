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
		Habitacion habitacion = repository.findByNumero(numero)
				.orElseThrow(() -> new HabitacionException(numero));

		habitacion.setHabitacionTipoId(newHabitacion.getHabitacionTipoId());
		habitacion.setClienteId(newHabitacion.getClienteId());

		return repository.save(habitacion);
	}

}
