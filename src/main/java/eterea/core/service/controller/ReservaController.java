/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.HabitacionMovimiento;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.model.dto.CreateHabitacionMovimientoDto;
import eterea.core.service.model.dto.reserva.CreateReservaDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

	@PostMapping("/")
	public ResponseEntity<Reserva> createReserva(@RequestBody CreateReservaDto createReservaDto) {
		return new ResponseEntity<>(service.createReserva(createReservaDto), HttpStatus.CREATED);
	}

	@GetMapping("/last")
	public ResponseEntity<Reserva> findLastReserva() {
		return new ResponseEntity<>(service.findLastReserva(), HttpStatus.OK);
	}

	@GetMapping("/no-facturadas")
	public ResponseEntity<List<Reserva>> findLastDaysVerificadasAndNoFacturadas(@RequestParam(defaultValue = "5") int days) {
		return new ResponseEntity<>(service.findLastDaysVerificadasAndNoFacturadas(days), HttpStatus.OK);
	}

	@PutMapping("/{reservaId}/verificar")
	public ResponseEntity<Void> verificarReserva(@PathVariable Long reservaId) {
		service.verificarReserva(reservaId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/{reservaId}/tipo-pendiente/{tipoPendienteId}")
	public ResponseEntity<Void> cambiarTipoPendiente(@PathVariable Long reservaId, @PathVariable byte tipoPendienteId) {
		service.cambiarTipoPendiente(reservaId, tipoPendienteId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
