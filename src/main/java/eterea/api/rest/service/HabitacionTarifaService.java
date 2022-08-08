/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.HabitacionTarifaNotFoundException;
import eterea.api.rest.model.HabitacionTarifa;
import eterea.api.rest.repository.IHabitacionTarifaRepository;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionTarifaService {

	@Autowired
	private IHabitacionTarifaRepository repository;

	public HabitacionTarifa findByUnique(Integer numero, Integer paxs) {
		return repository.findByNumeroAndPaxs(numero, paxs)
				.orElseThrow(() -> new HabitacionTarifaNotFoundException(numero, paxs));
	}

	public HabitacionTarifa add(HabitacionTarifa habitacionTarifa) {
		habitacionTarifa = repository.save(habitacionTarifa);
		return habitacionTarifa;
	}

	public HabitacionTarifa update(HabitacionTarifa habitacionTarifa, Long habitacionTarifaId) {
		return repository.findByHabitacionTarifaId(habitacionTarifaId)
				.orElseThrow(() -> new HabitacionTarifaNotFoundException(habitacionTarifaId));
	}

}
