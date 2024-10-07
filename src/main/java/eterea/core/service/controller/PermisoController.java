/**
 * 
 */
package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Permiso;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.PermisoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/permiso", "/permiso"})
public class PermisoController {

	private final PermisoService service;

	public PermisoController(PermisoService service) {
		this.service = service;
	}

	@GetMapping("/permiso/{usuario}/{opcion}")
	public ResponseEntity<Permiso> findByPermiso(@PathVariable String usuario, @PathVariable String opcion) {
		return new ResponseEntity<>(service.findByPermiso(usuario, opcion), HttpStatus.OK);
	}
}
