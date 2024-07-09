/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.ClienteGrupoCupo;
import eterea.core.api.rest.service.ClienteGrupoCupoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/api/core/clientegrupocupo")
public class ClienteGrupoCupoController {
	@Autowired
	private ClienteGrupoCupoService service;

	@GetMapping("/clientegrupo/{clienteId}/{grupoId}")
	public ResponseEntity<List<ClienteGrupoCupo>> findByClienteIdAndGrupoId(@PathVariable Long clienteId,
			@PathVariable Integer grupoId) {
		return new ResponseEntity<List<ClienteGrupoCupo>>(service.findByClienteIdAndGrupoId(clienteId, grupoId),
				HttpStatus.OK);
	}

	@GetMapping("/unique/{clienteId}/{grupoId}/{dias}")
	public ResponseEntity<ClienteGrupoCupo> findByUnique(@PathVariable Long clienteId, @PathVariable Integer grupoId,
			@PathVariable Integer dias) {
		return new ResponseEntity<ClienteGrupoCupo>(service.findByUnique(clienteId, grupoId, dias), HttpStatus.OK);
	}

	@GetMapping("/autonumerico/{clientegrupocupoId}")
	public ResponseEntity<ClienteGrupoCupo> findByAutonumerico(@PathVariable Long clientegrupocupoId) {
		return new ResponseEntity<ClienteGrupoCupo>(service.findByAutonumerico(clientegrupocupoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ClienteGrupoCupo> add(@RequestBody ClienteGrupoCupo clientegrupocupo) {
		return new ResponseEntity<ClienteGrupoCupo>(service.add(clientegrupocupo), HttpStatus.OK);
	}

	@PutMapping("/{clientegrupocupoId}")
	public ResponseEntity<ClienteGrupoCupo> update(@RequestBody ClienteGrupoCupo clientegrupocupo,
			@PathVariable Long clientegrupocupoId) {
		return new ResponseEntity<ClienteGrupoCupo>(service.update(clientegrupocupo, clientegrupocupoId),
				HttpStatus.OK);
	}

	@DeleteMapping("/{clienteId}/{grupoId}/{dias}")
	public ResponseEntity<Void> delete(@PathVariable Long clienteId, @PathVariable Integer grupoId,
			@PathVariable Integer dias) {
		service.deleteByUnique(clienteId, grupoId, dias);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
