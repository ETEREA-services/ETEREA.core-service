/**
 * 
 */
package eterea.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.Comprobante;
import eterea.api.rest.service.ComprobanteService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/comprobante")
public class ComprobanteController {

	@Autowired
	private ComprobanteService service;

	@GetMapping("/modulo/{modulo}/{debita}/{comprobanteId}")
	public ResponseEntity<List<Comprobante>> findAllByModulo(@PathVariable Integer modulo, @PathVariable Byte debita,
			@PathVariable Integer comprobanteId) {
		return new ResponseEntity<List<Comprobante>>(service.findAllByModulo(modulo, debita, comprobanteId),
				HttpStatus.OK);
	}

	@GetMapping("/{comprobanteId}")
	public ResponseEntity<Comprobante> findByComprobanteId(@PathVariable Integer comprobanteId) {
		return new ResponseEntity<Comprobante>(service.findByComprobanteId(comprobanteId), HttpStatus.OK);
	}
}
