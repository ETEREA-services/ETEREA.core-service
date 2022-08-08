/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ClienteNotFoundException;
import eterea.api.rest.model.Cliente;
import eterea.api.rest.model.view.ClienteSearch;
import eterea.api.rest.repository.IClienteRepository;
import eterea.api.rest.service.view.ClienteSearchService;

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
		return repository.findByClienteId(clienteId).orElseThrow(() -> new ClienteNotFoundException(clienteId));
	}

	public Cliente findByNumeroDocumento(String numeroDocumento) {
		return repository.findTopByNumeroDocumento(numeroDocumento)
				.orElseThrow(() -> new ClienteNotFoundException(numeroDocumento));
	}

	public Cliente findLast() {
		return repository.findTopByOrderByClienteIdDesc().orElseThrow(() -> new ClienteNotFoundException());
	}

	public Cliente add(Cliente cliente) {
		cliente = repository.save(cliente);
		return cliente;
	}

}
