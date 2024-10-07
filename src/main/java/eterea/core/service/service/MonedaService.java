/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.MonedaException;
import eterea.core.service.kotlin.model.Moneda;
import eterea.core.service.repository.MonedaRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class MonedaService {

	private final MonedaRepository repository;

	public MonedaService(MonedaRepository repository) {
		this.repository = repository;
	}

	public Moneda findByMonedaId(Integer monedaId) {
		return repository.findByMonedaId(monedaId).orElseThrow(() -> new MonedaException(monedaId));
	}

}
