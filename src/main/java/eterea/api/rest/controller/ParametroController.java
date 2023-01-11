/**
 * 
 */
package eterea.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.ParametroNotFoundException;
import eterea.api.rest.model.Parametro;
import eterea.api.rest.service.ParametroService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/parametro")
public class ParametroController {
	
	@Autowired
	private ParametroService service;

	@GetMapping("/last")
	public ResponseEntity<Parametro> findLast() {
		try {
			return new ResponseEntity<Parametro>(service.findLast(), HttpStatus.OK);
		} catch (ParametroNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
