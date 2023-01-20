/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ClienteMovimientoPrevioException;
import eterea.api.rest.model.ClienteMovimientoPrevio;
import eterea.api.rest.repository.IClienteMovimientoPrevioRepository;

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
