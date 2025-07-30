/**
 * 
 */
package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Negocio;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.NegocioService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/negocio", "/negocio"})
@Slf4j
public class NegocioController {
	
	private final NegocioService service;

    public NegocioController(NegocioService service) {
        this.service = service;
    }

	@GetMapping("/{negocioId}")
	public ResponseEntity<Negocio> findByNegocioId(@PathVariable Integer negocioId) {
		var negocio = service.findByNegocioId(negocioId);
        log.debug("Negocio -> {}", negocio.jsonify());
        return new ResponseEntity<>(negocio, HttpStatus.OK);
	}
}
