/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.ClienteMovimientoPrevioException;
import eterea.core.service.kotlin.model.ClienteMovimientoPrevio;
import eterea.core.service.repository.ClienteMovimientoPrevioRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteMovimientoPrevioService {

	private final ClienteMovimientoPrevioRepository repository;

	public ClienteMovimientoPrevioService(ClienteMovimientoPrevioRepository repository) {
		this.repository = repository;
	}

	public ClienteMovimientoPrevio findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId) {
		return repository.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId)
				.orElseThrow(() -> new ClienteMovimientoPrevioException(clienteMovimientoPrevioId));
	}

}
