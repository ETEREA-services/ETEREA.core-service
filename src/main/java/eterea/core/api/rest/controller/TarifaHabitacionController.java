/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.TarifaHabitacion;
import eterea.core.api.rest.service.TarifaHabitacionService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/tarifahabitacion")
public class TarifaHabitacionController {

	@Autowired
	private TarifaHabitacionService service;

	@GetMapping("/combo/{bloqueoEspecial}")
	public ResponseEntity<List<TarifaHabitacion>> findAllSinBloqueo(@PathVariable Boolean bloqueoEspecial) {
		return new ResponseEntity<List<TarifaHabitacion>>(service.findAllSinBloqueo(bloqueoEspecial), HttpStatus.OK);
	}
}
