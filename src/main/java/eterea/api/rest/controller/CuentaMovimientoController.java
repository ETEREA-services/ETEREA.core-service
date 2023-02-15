/**
 * 
 */
package eterea.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.CuentaMovimientoException;
import eterea.api.rest.model.dto.CuentaMovimientoDTO;
import eterea.api.rest.service.CuentaMovimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/cuentaMovimiento")
public class CuentaMovimientoController {

	@Autowired
	private CuentaMovimientoService service;

	@GetMapping("/{cuentaMovimientoId}")
	public ResponseEntity<CuentaMovimientoDTO> findByCuentaMovimientoId(@PathVariable Long cuentaMovimientoId) {
		try {
			return new ResponseEntity<CuentaMovimientoDTO>(service.findByCuentaMovimientoId(cuentaMovimientoId),
					HttpStatus.OK);
		} catch (CuentaMovimientoException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

}
