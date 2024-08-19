/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.MonedaException;
import eterea.core.api.rest.kotlin.model.Moneda;
import eterea.core.api.rest.repository.MonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class MonedaService {

	private final MonedaRepository repository;

	@Autowired
	public MonedaService(MonedaRepository repository) {
		this.repository = repository;
	}

	public Moneda findByMonedaId(Integer monedaId) {
		return repository.findByMonedaId(monedaId).orElseThrow(() -> new MonedaException(monedaId));
	}

}
