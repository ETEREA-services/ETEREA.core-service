/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.HabitacionException;
import eterea.core.service.kotlin.model.Habitacion;
import eterea.core.service.repository.HabitacionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class HabitacionService {

	private final HabitacionRepository repository;

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
