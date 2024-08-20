/**
 * 
 */
package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Permiso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.PermisoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/permiso", "/permiso"})
public class PermisoController {

	@Autowired
	private PermisoService service;

	@GetMapping("/permiso/{usuario}/{opcion}")
	public ResponseEntity<Permiso> findByPermiso(@PathVariable String usuario, @PathVariable String opcion) {
		return new ResponseEntity<>(service.findByPermiso(usuario, opcion), HttpStatus.OK);
	}
}
