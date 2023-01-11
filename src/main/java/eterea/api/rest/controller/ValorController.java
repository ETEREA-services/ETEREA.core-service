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

import eterea.api.rest.model.Valor;
import eterea.api.rest.service.ValorService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/valor")
public class ValorController {
	
	@Autowired
	private ValorService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Valor>> findAll() {
		return new ResponseEntity<List<Valor>>(service.findAll(), HttpStatus.OK);
	}

}
