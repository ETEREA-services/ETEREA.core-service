/**
 * 
 */
package eterea.api.rest.controller.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.service.facade.AlojamientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/alojamiento")
public class AlojamientoController {

	@Autowired
	private AlojamientoService service;

	@GetMapping("/saveHabitacion/{numero}/{paxs}/{habitacionTipoId}/{valorDefault}")
	public ResponseEntity<Boolean> saveHabitacion(@PathVariable Integer numero, @PathVariable Integer paxs,
			@PathVariable Integer habitacionTipoId, @PathVariable Boolean valorDefault) {
		return new ResponseEntity<Boolean>(service.saveHabitacion(numero, paxs, habitacionTipoId, valorDefault),
				HttpStatus.OK);
	}

}
