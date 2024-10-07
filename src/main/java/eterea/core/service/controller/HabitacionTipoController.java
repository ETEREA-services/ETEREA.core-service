/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.HabitacionTipo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.HabitacionTipoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/habitaciontipo", "/habitaciontipo"})
public class HabitacionTipoController {

	private final HabitacionTipoService service;

	public HabitacionTipoController(HabitacionTipoService service) {
		this.service = service;
	}

	@GetMapping("/")
	public ResponseEntity<List<HabitacionTipo>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
}
