/**
 * 
 */
package eterea.core.service.controller.facade;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.facade.AlojamientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/alojamiento", "/alojamiento"})
public class AlojamientoController {

	private final AlojamientoService service;

	public AlojamientoController(AlojamientoService service) {
		this.service = service;
	}

	@GetMapping("/saveHabitacion/{numero}/{paxs}/{habitacionTipoId}/{valorDefault}")
	public ResponseEntity<Boolean> saveHabitacion(@PathVariable Integer numero, @PathVariable Integer paxs,
			@PathVariable Integer habitacionTipoId, @PathVariable Boolean valorDefault) {
		return new ResponseEntity<>(service.saveHabitacion(numero, paxs, habitacionTipoId, valorDefault),
				HttpStatus.OK);
	}

}
