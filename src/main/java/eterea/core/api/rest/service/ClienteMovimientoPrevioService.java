/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ClienteMovimientoPrevioException;
import eterea.core.api.rest.kotlin.model.ClienteMovimientoPrevio;
import eterea.core.api.rest.repository.ClienteMovimientoPrevioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoPrevioService {

	@Autowired
	private ClienteMovimientoPrevioRepository repository;

	public ClienteMovimientoPrevio findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId) {
		return repository.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId)
				.orElseThrow(() -> new ClienteMovimientoPrevioException(clienteMovimientoPrevioId));
	}

}
