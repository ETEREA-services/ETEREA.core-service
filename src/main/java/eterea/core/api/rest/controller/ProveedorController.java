/**
 * 
 */
package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.api.rest.exception.ProveedorException;
import eterea.core.api.rest.service.ProveedorService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/core/proveedor")
public class ProveedorController {

	@Autowired
	private ProveedorService service;

	@GetMapping("/{proveedorId}")
	public ResponseEntity<Proveedor> findByProveedorId(@PathVariable Integer proveedorId) {
		try {
			return new ResponseEntity<Proveedor>(service.findByProveedorId(proveedorId), HttpStatus.OK);
		} catch (ProveedorException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}
}
