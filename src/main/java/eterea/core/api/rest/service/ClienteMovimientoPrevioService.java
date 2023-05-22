/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ClienteMovimientoPrevioException;
import eterea.core.api.rest.repository.IClienteMovimientoPrevioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ClienteMovimientoPrevio;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoPrevioService {

	@Autowired
	private IClienteMovimientoPrevioRepository repository;

	public ClienteMovimientoPrevio findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId) {
		return repository.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId)
				.orElseThrow(() -> new ClienteMovimientoPrevioException(clienteMovimientoPrevioId));
	}

}
