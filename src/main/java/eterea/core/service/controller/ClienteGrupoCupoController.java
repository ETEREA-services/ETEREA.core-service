/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.ClienteGrupoCupo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ClienteGrupoCupoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/clientegrupocupo", "/clientegrupocupo"})
public class ClienteGrupoCupoController {

	private final ClienteGrupoCupoService service;

	public ClienteGrupoCupoController(ClienteGrupoCupoService service) {
		this.service = service;
	}

	@GetMapping("/clientegrupo/{clienteId}/{grupoId}")
	public ResponseEntity<List<ClienteGrupoCupo>> findByClienteIdAndGrupoId(@PathVariable Long clienteId,
																			@PathVariable Integer grupoId) {
		return new ResponseEntity<>(service.findByClienteIdAndGrupoId(clienteId, grupoId),
				HttpStatus.OK);
	}

	@GetMapping("/unique/{clienteId}/{grupoId}/{dias}")
	public ResponseEntity<ClienteGrupoCupo> findByUnique(@PathVariable Long clienteId, @PathVariable Integer grupoId,
			@PathVariable Integer dias) {
		return new ResponseEntity<>(service.findByUnique(clienteId, grupoId, dias), HttpStatus.OK);
	}

	@GetMapping("/autonumerico/{clientegrupocupoId}")
	public ResponseEntity<ClienteGrupoCupo> findByAutonumerico(@PathVariable Long clientegrupocupoId) {
		return new ResponseEntity<>(service.findByAutonumerico(clientegrupocupoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ClienteGrupoCupo> add(@RequestBody ClienteGrupoCupo clientegrupocupo) {
		return new ResponseEntity<>(service.add(clientegrupocupo), HttpStatus.OK);
	}

	@PutMapping("/{clientegrupocupoId}")
	public ResponseEntity<ClienteGrupoCupo> update(@RequestBody ClienteGrupoCupo clientegrupocupo,
			@PathVariable Long clientegrupocupoId) {
		return new ResponseEntity<>(service.update(clientegrupocupo, clientegrupocupoId),
				HttpStatus.OK);
	}

	@DeleteMapping("/{clienteId}/{grupoId}/{dias}")
	public ResponseEntity<Void> delete(@PathVariable Long clienteId, @PathVariable Integer grupoId,
			@PathVariable Integer dias) {
		service.deleteByUnique(clienteId, grupoId, dias);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
