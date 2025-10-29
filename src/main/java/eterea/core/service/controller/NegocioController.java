/**
 * 
 */
package eterea.core.service.controller;

import eterea.core.service.exception.NegocioException;
import eterea.core.service.kotlin.model.Negocio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.NegocioService;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/negocio", "/negocio"})
@RequiredArgsConstructor
public class NegocioController {
	
	private final NegocioService service;

	@GetMapping("/{negocioId}")
	public ResponseEntity<Negocio> findByNegocioId(@PathVariable Integer negocioId) {
        try {
            return new ResponseEntity<>(service.findByNegocioId(negocioId), HttpStatus.OK);
        } catch (NegocioException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
	}
}
