/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.ClienteMovimientoPrevioException;
import eterea.core.service.kotlin.model.ClienteMovimientoPrevio;
import eterea.core.service.repository.ClienteMovimientoPrevioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ClienteMovimientoPrevioService {

	private final ClienteMovimientoPrevioRepository repository;

	public ClienteMovimientoPrevio findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId) {
		return repository.findByClienteMovimientoPrevioId(clienteMovimientoPrevioId)
				.orElseThrow(() -> new ClienteMovimientoPrevioException(clienteMovimientoPrevioId));
	}

}
