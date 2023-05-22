/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.HabitacionException;
import eterea.core.api.rest.repository.IHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Habitacion;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionService {

	@Autowired
	private IHabitacionRepository repository;

	public Habitacion findByNumero(Integer numero) {
		return repository.findByNumero(numero).orElseThrow(() -> new HabitacionException(numero));
	}

	public Habitacion update(Habitacion newHabitacion, Integer numero) {
		return repository.findByNumero(numero).map(habitacion -> {
			habitacion = new Habitacion(numero, newHabitacion.getHabitacionTipoId(), newHabitacion.getClienteId(), newHabitacion.getHabitacionId());
			habitacion = repository.save(habitacion);
			return habitacion;
		}).orElseThrow(() -> new HabitacionException(numero));
	}

}
