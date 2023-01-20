/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.HabitacionException;
import eterea.api.rest.model.Habitacion;
import eterea.api.rest.repository.IHabitacionRepository;

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
