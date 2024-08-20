/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import eterea.core.api.rest.kotlin.model.Cliente;
import eterea.core.api.rest.kotlin.model.view.ClienteSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.api.rest.exception.ClienteException;
import eterea.core.api.rest.service.ClienteService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/cliente", "/cliente"})
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping("/search/{chain}")
	public ResponseEntity<List<ClienteSearch>> findAllBySearch(@PathVariable String chain) {
		return new ResponseEntity<List<ClienteSearch>>(service.findAllBySearch(chain), HttpStatus.OK);
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> findByClienteId(@PathVariable Long clienteId) {
		try {
			return new ResponseEntity<>(service.findByClienteId(clienteId), HttpStatus.OK);
		} catch (ClienteException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/numeroDocumento/{numeroDocumento}")
	public ResponseEntity<Cliente> findByNumeroDocumento(@PathVariable String numeroDocumento) {
		try {
			return new ResponseEntity<>(service.findByNumeroDocumento(numeroDocumento), HttpStatus.OK);
		} catch (ClienteException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/last")
	public ResponseEntity<Cliente> findLast() {
		try {
			return new ResponseEntity<>(service.findLast(), HttpStatus.OK);
		} catch (ClienteException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/")
	public ResponseEntity<Cliente> add(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(service.add(cliente), HttpStatus.OK);
	}

}
