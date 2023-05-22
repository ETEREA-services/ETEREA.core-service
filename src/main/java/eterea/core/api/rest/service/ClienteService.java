/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.ClienteException;
import eterea.core.api.rest.repository.IClienteRepository;
import eterea.core.api.rest.service.view.ClienteSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Cliente;
import eterea.core.api.rest.model.view.ClienteSearch;

/**
 * @author daniel
 *
 */
@Service
public class ClienteService {

	@Autowired
	private IClienteRepository repository;

	@Autowired
	private ClienteSearchService clienteSearchService;

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
		return repository.findTopByOrderByClienteIdDesc().orElseThrow(() -> new ClienteException());
	}

	public Cliente add(Cliente cliente) {
		cliente = repository.save(cliente);
		return cliente;
	}

}
