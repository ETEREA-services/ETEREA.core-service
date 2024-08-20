/**
 * 
 */
package eterea.core.api.rest.controller.view;

import java.util.List;

import eterea.core.api.rest.kotlin.model.view.ClienteActivado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.view.ClienteActivadoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/clienteactivado", "/clienteactivado"})
public class ClienteActivadoController {
	@Autowired
	private ClienteActivadoService service;
	
	@GetMapping("/")
	public ResponseEntity<List<ClienteActivado>> findAll() {
		return new ResponseEntity<List<ClienteActivado>>(service.findAll(), HttpStatus.OK);
	}
}
