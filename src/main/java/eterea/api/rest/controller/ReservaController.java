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

import eterea.api.rest.model.Reserva;
import eterea.api.rest.service.facade.ReservaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/reserva")
public class ReservaController {

	@Autowired
	private ReservaService service;

	@GetMapping("/pendientes")
	public ResponseEntity<List<Reserva>> findTopPendientes() {
		return new ResponseEntity<List<Reserva>>(service.findTopPendientes(), HttpStatus.OK);
	}
	
}
