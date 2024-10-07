/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.TarifaHabitacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.TarifaHabitacionService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/tarifahabitacion", "/tarifahabitacion"})
public class TarifaHabitacionController {

	private final TarifaHabitacionService service;

	public TarifaHabitacionController(TarifaHabitacionService service) {
		this.service = service;
	}

	@GetMapping("/combo/{bloqueoEspecial}")
	public ResponseEntity<List<TarifaHabitacion>> findAllSinBloqueo(@PathVariable Boolean bloqueoEspecial) {
		return new ResponseEntity<>(service.findAllSinBloqueo(bloqueoEspecial), HttpStatus.OK);
	}
}
