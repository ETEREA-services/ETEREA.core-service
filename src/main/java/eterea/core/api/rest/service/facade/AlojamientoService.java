/**
 * 
 */
package eterea.core.api.rest.service.facade;

import eterea.core.api.rest.exception.HabitacionException;
import eterea.core.api.rest.exception.HabitacionTarifaException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Habitacion;
import eterea.core.api.rest.model.HabitacionTarifa;
import eterea.core.api.rest.service.HabitacionService;
import eterea.core.api.rest.service.HabitacionTarifaService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class AlojamientoService {

	@Autowired
	private HabitacionService habitacionService;

	@Autowired
	private HabitacionTarifaService habitacionTarifaService;

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
