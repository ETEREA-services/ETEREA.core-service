/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import eterea.core.api.rest.kotlin.model.Legajo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.LegajoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/core/legajo")
public class LegajoController {
	
	@Autowired
	private LegajoService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Legajo>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
}
