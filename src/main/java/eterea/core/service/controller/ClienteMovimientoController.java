/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.ClienteMovimiento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ClienteMovimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/clienteMovimiento", "/clienteMovimiento"})
public class ClienteMovimientoController {

	private final ClienteMovimientoService service;

	public ClienteMovimientoController(ClienteMovimientoService service) {
		this.service = service;
	}

	@GetMapping("/asociable/{clienteId}")
	public ResponseEntity<List<ClienteMovimiento>> findTop200Asociables(@PathVariable Long clienteId) {
		return new ResponseEntity<>(service.findTop200Asociables(clienteId), HttpStatus.OK);
	}

	@GetMapping("/findAllByReservaIds/{reservaIds}")
	public ResponseEntity<List<ClienteMovimiento>> findAllByReservaIds(@PathVariable List<Long> reservaIds) {
		return new ResponseEntity<>(service.findAllByReservaIds(reservaIds), HttpStatus.OK);
	}

	@GetMapping("/reserva/{reservaId}")
	public ResponseEntity<List<ClienteMovimiento>> findAllByReservaId(@PathVariable Long reservaId) {
		return new ResponseEntity<>(service.findAllByReservaId(reservaId), HttpStatus.OK);
	}

	@GetMapping("/last/{puntoVenta}/{letraComprobante}")
	public ResponseEntity<Long> nextNumeroFactura(@PathVariable Integer puntoVenta,
			@PathVariable String letraComprobante) {
		return new ResponseEntity<>(service.nextNumeroFactura(puntoVenta, letraComprobante), HttpStatus.OK);
	}

	@GetMapping("/{clienteMovimientoId}")
	public ResponseEntity<ClienteMovimiento> findByClienteMovimientoId(@PathVariable Long clienteMovimientoId) {
		return new ResponseEntity<>(service.findByClienteMovimientoId(clienteMovimientoId),
				HttpStatus.OK);
	}

}
