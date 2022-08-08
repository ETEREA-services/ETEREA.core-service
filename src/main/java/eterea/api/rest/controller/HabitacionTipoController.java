/**
 * 
 */
package eterea.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.HabitacionTipo;
import eterea.api.rest.service.HabitacionTipoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/habitaciontipo")
public class HabitacionTipoController {

	@Autowired
	private HabitacionTipoService service;

	@GetMapping("/")
	public ResponseEntity<List<HabitacionTipo>> findAll() {
		return new ResponseEntity<List<HabitacionTipo>>(service.findAll(), HttpStatus.OK);
	}
}
