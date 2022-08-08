/**
 * 
 */
package eterea.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.Legajo;
import eterea.api.rest.service.LegajoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/legajo")
public class LegajoController {
	
	@Autowired
	private LegajoService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Legajo>> findAll() {
		return new ResponseEntity<List<Legajo>>(service.findAll(), HttpStatus.OK);
	}
}
