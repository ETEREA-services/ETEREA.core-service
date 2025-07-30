/**
 * 
 */
package eterea.core.service.controller.view;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.view.UsuarioVencimiento;
import eterea.core.service.service.view.UsuarioVencimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/usuarioVencimiento", "/usuarioVencimiento"})
public class UsuarioVencimientoController {

	private final UsuarioVencimientoService service;

	public UsuarioVencimientoController(UsuarioVencimientoService service) {
		this.service = service;
	}

	@GetMapping("/today")
	public ResponseEntity<List<UsuarioVencimiento>> findAllToday() {
		return new ResponseEntity<>(service.findAllToday(), HttpStatus.OK);
	}

}
