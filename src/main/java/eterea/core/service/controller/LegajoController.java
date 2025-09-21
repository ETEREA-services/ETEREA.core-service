/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.Legajo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.LegajoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/legajo", "/legajo"})
@RequiredArgsConstructor
public class LegajoController {
	
	private final LegajoService service;

	@GetMapping("/")
	public ResponseEntity<List<Legajo>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

}
