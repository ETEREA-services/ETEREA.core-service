/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.HabitacionTipo;
import eterea.core.api.rest.service.HabitacionTipoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/core/habitaciontipo")
public class HabitacionTipoController {

	@Autowired
	private HabitacionTipoService service;

	@GetMapping("/")
	public ResponseEntity<List<HabitacionTipo>> findAll() {
		return new ResponseEntity<List<HabitacionTipo>>(service.findAll(), HttpStatus.OK);
	}
}
