/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.HabitacionTarifaException;
import eterea.core.api.rest.kotlin.model.HabitacionTarifa;
import eterea.core.api.rest.repository.HabitacionTarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionTarifaService {

	@Autowired
	private HabitacionTarifaRepository repository;

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
