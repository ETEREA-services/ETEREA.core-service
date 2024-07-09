/**
 * 
 */
package eterea.core.api.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.api.rest.kotlin.model.Negocio;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/core/negocio")
@Slf4j
public class NegocioController {
	
	@Autowired
	private NegocioService service;

	@GetMapping("/{negocioId}")
	public ResponseEntity<Negocio> findByNegocioId(@PathVariable Integer negocioId) {
		var negocio = service.findByNegocioId(negocioId);
        try {
            log.debug("Negocio -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(negocio));
        } catch (JsonProcessingException e) {
            log.debug("Negocio -> null");
        }
        return new ResponseEntity<>(negocio, HttpStatus.OK);
	}
}
