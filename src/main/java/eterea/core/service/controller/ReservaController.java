/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.Reserva;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ReservaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/reserva", "/reserva"})
public class ReservaController {

	private final ReservaService service;

	public ReservaController(ReservaService service) {
		this.service = service;
	}

	@GetMapping("/pendientes")
	public ResponseEntity<List<Reserva>> findTopPendientes() {
		return new ResponseEntity<>(service.findTopPendientes(), HttpStatus.OK);
	}

	@GetMapping("/{reservaId}")
	public ResponseEntity<Reserva> findByReservaId(@PathVariable Long reservaId) {
		return new ResponseEntity<>(service.findByReservaId(reservaId), HttpStatus.OK);
	}

	@GetMapping("/{reservaId}/articulos")
	public ResponseEntity<List<ReservaArticulo>> findAllReservaArticulos(@PathVariable Long reservaId) {
		return new ResponseEntity<>(service.findAllReservaArticulos(reservaId), HttpStatus.OK);
	}
	
}
