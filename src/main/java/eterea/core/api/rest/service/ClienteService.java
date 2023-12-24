/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.ClienteException;
import eterea.core.api.rest.kotlin.model.Cliente;
import eterea.core.api.rest.repository.IClienteRepository;
import eterea.core.api.rest.service.view.ClienteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.view.ClienteSearch;

/**
 * @author daniel
 *
 */
@Service
public class ClienteService {

	private final IClienteRepository repository;

	private final ClienteSearchService clienteSearchService;

	@Autowired
	public ClienteService(IClienteRepository repository, ClienteSearchService clienteSearchService) {
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
		return repository.findTopByNumeroDocumento(numeroDocumento)
				.orElseThrow(() -> new ClienteException(numeroDocumento));
	}

	public Cliente findLast() {
		return repository.findTopByOrderByClienteIdDesc().orElseThrow(ClienteException::new);
	}

	public Cliente add(Cliente cliente) {
		return repository.save(cliente);
	}

}
