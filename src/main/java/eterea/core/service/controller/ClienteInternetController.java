/**
 * 
 */
package eterea.core.service.controller;

import eterea.core.service.kotlin.model.ClienteInternet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ClienteInternetService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/clienteinternet", "/clienteinternet"})
public class ClienteInternetController {

	private final ClienteInternetService service;

	public ClienteInternetController(ClienteInternetService service) {
		this.service = service;
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteInternet> findById(@PathVariable Long clienteId) {
		return new ResponseEntity<>(service.findById(clienteId), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<ClienteInternet> add(@RequestBody ClienteInternet clienteinternet) {
		return new ResponseEntity<>(service.add(clienteinternet), HttpStatus.OK);
	}
	@GetMapping("/add")
	public ResponseEntity<ClienteInternet> addByGet(@RequestParam("clienteID") Long clienteId, @RequestParam String password) {
		return add(new ClienteInternet(clienteId, password));
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteInternet> update(@RequestBody ClienteInternet clienteinternet, @PathVariable Long clienteId) {
		return new ResponseEntity<>(service.update(clienteinternet, clienteId), HttpStatus.OK);
	}
	@GetMapping("/update")
	public ResponseEntity<ClienteInternet> updateByGet(@RequestParam("clienteID") Long clienteId, @RequestParam String password) {
		return update(new ClienteInternet(clienteId, password), clienteId);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
		service.delete(clienteId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
