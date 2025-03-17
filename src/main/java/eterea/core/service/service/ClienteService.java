/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.ClienteException;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.view.ClienteSearch;
import eterea.core.service.repository.ClienteRepository;
import eterea.core.service.service.view.ClienteSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ClienteService {

	private final ClienteRepository repository;
	private final ClienteSearchService clienteSearchService;

	public ClienteService(ClienteRepository repository, ClienteSearchService clienteSearchService) {
		this.repository = repository;
		this.clienteSearchService = clienteSearchService;
	}

	public List<ClienteSearch> findAllBySearch(String search) {
		return clienteSearchService.findAllBySearch(search);
	}

	public Cliente findByClienteId(Long clienteId) {
		return repository.findByClienteId(clienteId).orElseThrow(() -> new ClienteException(clienteId));
	}

	public Cliente findByNumeroDocumento(String numeroDocumento) {
		log.debug("Processing findByNumeroDocumento");
		return repository.findTopByNumeroDocumento(numeroDocumento)
				.orElseThrow(() -> new ClienteException(numeroDocumento));
	}

	public Cliente findLast() {
		return repository.findTopByOrderByClienteIdDesc().orElseThrow(ClienteException::new);
	}

	public Cliente add(Cliente cliente) {
		return repository.save(cliente);
	}

	// NUEVO METODO PARA BUSCAR CLIENTES CON "LIKE"
	public List<Cliente> buscar(String searchTerm) {
		return repository.findByNombreContainingIgnoreCase(searchTerm);
	}

}
