/**
 * 
 */
package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.CuentaMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.api.rest.exception.CuentaMovimientoException;
import eterea.core.api.rest.service.CuentaMovimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/core/cuentaMovimiento")
public class CuentaMovimientoController {

	private final CuentaMovimientoService service;

	@Autowired
	public CuentaMovimientoController(CuentaMovimientoService service) {
		this.service = service;
	}

	@GetMapping("/{cuentaMovimientoId}")
	public ResponseEntity<CuentaMovimiento> findByCuentaMovimientoId(@PathVariable Long cuentaMovimientoId) {
		try {
			return new ResponseEntity<>(service.findByCuentaMovimientoId(cuentaMovimientoId),
					HttpStatus.OK);
		} catch (CuentaMovimientoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
