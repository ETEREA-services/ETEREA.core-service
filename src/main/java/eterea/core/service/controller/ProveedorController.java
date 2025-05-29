/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.service.exception.ProveedorException;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.service.ProveedorService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/proveedor", "/proveedor"})
public class ProveedorController {

	private final ProveedorService service;

	public ProveedorController(ProveedorService service) {
		this.service = service;
	}

	@GetMapping("/{proveedorId}")
	public ResponseEntity<Proveedor> findByProveedorId(@PathVariable Integer proveedorId) {
		try {
			return new ResponseEntity<Proveedor>(service.findByProveedorId(proveedorId), HttpStatus.OK);
		} catch (ProveedorException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/transporte")
	public ResponseEntity<List<Proveedor>> findAllByTransporte() {
		return new ResponseEntity<>(service.findAllByTransporte(), HttpStatus.OK);
	}
}
