/**
 * 
 */
package eterea.core.service.controller.view;

import java.util.List;

import eterea.core.service.kotlin.model.view.ClienteActivado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.view.ClienteActivadoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/clienteactivado", "/clienteactivado"})
public class ClienteActivadoController {

	private final ClienteActivadoService service;

	public ClienteActivadoController(ClienteActivadoService service) {
		this.service = service;
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ClienteActivado>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}
}