/**
 * 
 */
package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Habitacion;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.HabitacionService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/habitacion", "/habitacion"})
public class HabitacionController {

	private final HabitacionService service;

	public HabitacionController(HabitacionService service) {
		this.service = service;
	}

	@GetMapping("/{numero}")
	public ResponseEntity<Habitacion> findByNumero(@PathVariable Integer numero) {
		return new ResponseEntity<>(service.findByNumero(numero), HttpStatus.OK);
	}
}
