/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.HabitacionTarifaException;
import eterea.core.service.kotlin.model.HabitacionTarifa;
import eterea.core.service.repository.HabitacionTarifaRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionTarifaService {

	private final HabitacionTarifaRepository repository;

	public HabitacionTarifaService(HabitacionTarifaRepository repository) {
		this.repository = repository;
	}

	public HabitacionTarifa findByUnique(Integer numero, Integer paxs) {
		return repository.findByNumeroAndPaxs(numero, paxs)
				.orElseThrow(() -> new HabitacionTarifaException(numero, paxs));
	}

	public HabitacionTarifa add(HabitacionTarifa habitacionTarifa) {
		habitacionTarifa = repository.save(habitacionTarifa);
		return habitacionTarifa;
	}

	public HabitacionTarifa update(HabitacionTarifa habitacionTarifa, Long habitacionTarifaId) {
		return repository.findByHabitacionTarifaId(habitacionTarifaId)
				.orElseThrow(() -> new HabitacionTarifaException(habitacionTarifaId));
	}

}
