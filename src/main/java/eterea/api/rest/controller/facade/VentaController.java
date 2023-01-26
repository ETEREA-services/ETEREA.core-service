/**
 * 
 */
package eterea.api.rest.controller.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.ClienteMovimientoNotFoundException;
import eterea.api.rest.model.dto.VentaDTO;
import eterea.api.rest.service.facade.VentaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/venta")
public class VentaController {

	@Autowired
	private VentaService service;

	@GetMapping("/comprobante/{comprobanteId}/{puntoVenta}/{numeroComprobante}")
	public ResponseEntity<VentaDTO> findByComprobante(@PathVariable Integer comprobanteId,
			@PathVariable Integer puntoVenta, @PathVariable Long numeroComprobante) {
		try {
			return new ResponseEntity<VentaDTO>(service.findByComprobante(comprobanteId, puntoVenta, numeroComprobante),
					HttpStatus.OK);
		} catch (ClienteMovimientoNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<VentaDTO> findById(@PathVariable String id) {
		try {
			return new ResponseEntity<VentaDTO>(service.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PostMapping("/")
	public ResponseEntity<VentaDTO> create(@RequestBody VentaDTO venta) {
		try {
			return new ResponseEntity<VentaDTO>(service.create(venta), HttpStatus.CREATED);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<VentaDTO> update(@RequestBody VentaDTO venta, @PathVariable String id) {
		try {
			return new ResponseEntity<VentaDTO>(service.update(venta, id), HttpStatus.OK);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
