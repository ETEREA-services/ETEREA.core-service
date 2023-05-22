/**
 * 
 */
package eterea.core.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import eterea.core.api.rest.model.ClienteInternet;
import eterea.core.api.rest.service.ClienteInternetService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/clienteinternet")
public class ClienteInternetController {
	@Autowired
	private ClienteInternetService service;
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteInternet> findById(@PathVariable Long clienteId) {
		return new ResponseEntity<ClienteInternet>(service.findById(clienteId), HttpStatus.OK);
	}
	
	@PostMapping("/")
	public ResponseEntity<ClienteInternet> add(@RequestBody ClienteInternet clienteinternet) {
		return new ResponseEntity<ClienteInternet>(service.add(clienteinternet), HttpStatus.OK);
	}
	@GetMapping("/add")
	public ResponseEntity<ClienteInternet> addByGet(@RequestParam("clienteID") Long clienteId, @RequestParam String password) {
		return add(new ClienteInternet(clienteId, password));
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteInternet> update(@RequestBody ClienteInternet clienteinternet, @PathVariable Long clienteId) {
		return new ResponseEntity<ClienteInternet>(service.update(clienteinternet, clienteId), HttpStatus.OK);
	}
	@GetMapping("/update")
	public ResponseEntity<ClienteInternet> updateByGet(@RequestParam("clienteID") Long clienteId, @RequestParam String password) {
		return update(new ClienteInternet(clienteId, password), clienteId);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
		service.delete(clienteId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
