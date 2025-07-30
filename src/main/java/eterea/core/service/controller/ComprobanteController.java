/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.exception.ComprobanteException;
import eterea.core.service.kotlin.model.Comprobante;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ComprobanteService;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/comprobante", "/comprobante"})
public class ComprobanteController {

	private final ComprobanteService service;

	public ComprobanteController(ComprobanteService service) {
		this.service = service;
	}

	@GetMapping("/modulo/{modulo}/{debita}/{comprobanteId}")
	public ResponseEntity<List<Comprobante>> findAllByModulo(@PathVariable Integer modulo,
															 @PathVariable Byte debita,
															 @PathVariable Integer comprobanteId) {
		return new ResponseEntity<>(service.findAllByModulo(modulo, debita, comprobanteId),
				HttpStatus.OK);
	}

	@GetMapping("/disponibles")
	public ResponseEntity<List<Integer>> findAllDisponibles() {
		return new ResponseEntity<>(service.findAllDisponibles(), HttpStatus.OK);
	}

	@GetMapping("/{comprobanteId}")
	public ResponseEntity<Comprobante> findByComprobanteId(@PathVariable Integer comprobanteId) {
		try {
			return new ResponseEntity<>(service.findByComprobanteId(comprobanteId), HttpStatus.OK);
		} catch (ComprobanteException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
