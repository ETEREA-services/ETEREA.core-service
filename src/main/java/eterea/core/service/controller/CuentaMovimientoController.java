/**
 * 
 */
package eterea.core.service.controller;

import eterea.core.service.model.CuentaMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.service.exception.CuentaMovimientoException;
import eterea.core.service.service.CuentaMovimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/cuentaMovimiento", "/cuentaMovimiento"})
@RequiredArgsConstructor
public class CuentaMovimientoController {

	private final CuentaMovimientoService service;

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
