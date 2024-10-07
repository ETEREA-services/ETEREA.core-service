/**
 * 
 */
package eterea.core.service.service.facade;

import eterea.core.service.exception.HabitacionException;
import eterea.core.service.exception.HabitacionTarifaException;
import eterea.core.service.kotlin.model.Habitacion;
import eterea.core.service.kotlin.model.HabitacionTarifa;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import eterea.core.service.service.HabitacionService;
import eterea.core.service.service.HabitacionTarifaService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class AlojamientoService {

	private final HabitacionService habitacionService;
	private final HabitacionTarifaService habitacionTarifaService;

	public AlojamientoService(HabitacionService habitacionService, HabitacionTarifaService habitacionTarifaService) {
		this.habitacionService = habitacionService;
		this.habitacionTarifaService = habitacionTarifaService;
	}

	@Transactional
	public Boolean saveHabitacion(Integer numero, Integer paxs, Integer habitacionTipoId, Boolean valorDefault) {
		if (valorDefault) {
			try {
				Habitacion habitacion = habitacionService.findByNumero(numero);
				habitacion.setHabitacionTipoId(habitacionTipoId);
				habitacion = habitacionService.update(habitacion, numero);
			} catch (HabitacionException e) {
				log.debug("Habitacion {} NO Encontrada", numero);
				return false;
			}
		}

		Long habitacionTarifaId = null;
		HabitacionTarifa habitacionTarifa = null;
		try {
			habitacionTarifa = habitacionTarifaService.findByUnique(numero, paxs);
			habitacionTarifaId = habitacionTarifa.getHabitacionTarifaId();
		} catch (HabitacionTarifaException e) {
			log.debug("Tarifa NO Existe");
		}
		habitacionTarifa = new HabitacionTarifa(habitacionTarifaId, numero, paxs, habitacionTipoId);
		if (habitacionTarifaId == null) {
			habitacionTarifa = habitacionTarifaService.add(habitacionTarifa);
		} else {
			habitacionTarifa = habitacionTarifaService.update(habitacionTarifa, habitacionTarifaId);
		}
		return true;
	}

}
