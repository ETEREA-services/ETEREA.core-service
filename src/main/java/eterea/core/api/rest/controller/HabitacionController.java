/**
 * 
 */
package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Habitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.HabitacionService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/core/habitacion")
public class HabitacionController {

	@Autowired
	private HabitacionService service;

	@GetMapping("/{numero}")
	public ResponseEntity<Habitacion> findByNumero(@PathVariable Integer numero) {
		return new ResponseEntity<>(service.findByNumero(numero), HttpStatus.OK);
	}
}
