/**
 * 
 */
package eterea.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.PuntoVentaException;
import eterea.api.rest.model.PuntoVenta;
import eterea.api.rest.service.PuntoVentaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/puntoVenta")
public class PuntoVentaController {

	@Autowired
	private PuntoVentaService service;

	@GetMapping("/{numero}")
	public ResponseEntity<PuntoVenta> findByNumero(@PathVariable Integer numero) {
		try {
			return new ResponseEntity<PuntoVenta>(service.findByNumero(numero), HttpStatus.OK);
		} catch (PuntoVentaException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
