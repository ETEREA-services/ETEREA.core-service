/**
 * 
 */
package eterea.core.api.rest.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.view.UsuarioVencimiento;
import eterea.core.api.rest.service.view.UsuarioVencimientoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/usuarioVencimiento")
public class UsuarioVencimientoController {

	@Autowired
	private UsuarioVencimientoService service;

	@GetMapping("/today")
	public ResponseEntity<List<UsuarioVencimiento>> findAllToday() {
		return new ResponseEntity<List<UsuarioVencimiento>>(service.findAllToday(), HttpStatus.OK);
	}

}
