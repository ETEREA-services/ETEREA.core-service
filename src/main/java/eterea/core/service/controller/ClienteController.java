/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.view.ClienteSearch;
import eterea.core.service.model.dto.PageDto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.service.exception.ClienteException;
import eterea.core.service.service.ClienteService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({ "/api/core/cliente", "/cliente" })
public class ClienteController {

	private final ClienteService service;

	public ClienteController(ClienteService service) {
		this.service = service;
	}

	@GetMapping("/search/{chain}")
	public ResponseEntity<List<ClienteSearch>> findAllBySearch(@PathVariable String chain) {
		return new ResponseEntity<>(service.findAllBySearch(chain), HttpStatus.OK);
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

	/*
	 * @author sebastian
	 */

	@GetMapping("/buscar/{searchTerm}")
	public ResponseEntity<List<Cliente>> buscar(@PathVariable String searchTerm,
			@RequestParam(defaultValue = "false") boolean incluyeBloqueados) {
		return new ResponseEntity<>(service.buscar(searchTerm, incluyeBloqueados), HttpStatus.OK);
	}

	@GetMapping("/tipoDocumento/{documentoId}/numeroDocumento/{numeroDocumento}")
	public ResponseEntity<Cliente> findByNumeroDocumentoAndDocumentoId(@PathVariable Integer documentoId,
			@PathVariable String numeroDocumento) {
		try {
			return new ResponseEntity<>(
					service.findByNumeroDocumentoAndDocumentoId(numeroDocumento, documentoId),
					HttpStatus.OK);
		} catch (ClienteException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PostMapping("/createOrGet")
	public ResponseEntity<Cliente> createOrGet(@RequestBody Cliente cliente) {
		return new ResponseEntity<>(service.createOrGet(cliente), HttpStatus.OK);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> update(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		return new ResponseEntity<>(service.update(clienteId, cliente), HttpStatus.OK);
	}

	@GetMapping("/byIds")
	public ResponseEntity<List<Cliente>> findAllByIds(@RequestParam List<Long> clienteIds) {
		return new ResponseEntity<>(service.findAllByIds(clienteIds), HttpStatus.OK);
	}

	@GetMapping("/cuit/{cuit}")
	public ResponseEntity<Cliente> findByCuit(@PathVariable String cuit) {
		try {
			return new ResponseEntity<>(service.findByCuit(cuit), HttpStatus.OK);
		} catch (ClienteException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/paginated")
	public ResponseEntity<PageDto<Cliente>> findAllPaginated(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "50") int size) {
		return new ResponseEntity<>(service.findAllPaginated(page, size), HttpStatus.OK);
	}

}
