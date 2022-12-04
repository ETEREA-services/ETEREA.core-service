/**
 * 
 */
package eterea.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.ClienteMovimiento;
import eterea.api.rest.service.ClienteMovimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/clienteMovimiento")
public class ClienteMovimientoController {

	@Autowired
	private ClienteMovimientoService service;

	@GetMapping("/asociable/{clienteId}")
	public ResponseEntity<List<ClienteMovimiento>> findAllAsociables(@PathVariable Long clienteId) {
		return new ResponseEntity<List<ClienteMovimiento>>(service.findAllAsociables(clienteId), HttpStatus.OK);
	}

	@GetMapping("/last/{puntoVenta}/{letraComprobante}")
	public ResponseEntity<Long> nextNumeroFactura(@PathVariable Integer puntoVenta,
			@PathVariable String letraComprobante) {
		return new ResponseEntity<Long>(service.nextNumeroFactura(puntoVenta, letraComprobante), HttpStatus.OK);
	}

	@GetMapping("/{clienteMovimientoId}")
	public ResponseEntity<ClienteMovimiento> findByClientemovimientoId(@PathVariable Long clienteMovimientoId) {
		return new ResponseEntity<ClienteMovimiento>(service.findByClienteMovimientoId(clienteMovimientoId),
				HttpStatus.OK);
	}

}
