/**
 * 
 */
package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Negocio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.NegocioService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/negocio")
public class NegocioController {
	
	@Autowired
	private NegocioService service;

	@GetMapping("/{negocioId}")
	public ResponseEntity<Negocio> findByNegocioId(@PathVariable Integer negocioId) {
		return new ResponseEntity<>(service.findByNegocioId(negocioId), HttpStatus.OK);
	}
}
