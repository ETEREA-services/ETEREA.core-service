/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.MonedaException;
import eterea.core.api.rest.repository.IMonedaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Moneda;

/**
 * @author daniel
 *
 */
@Service
public class MonedaService {

	@Autowired
	private IMonedaRepository repository;

	public Moneda findByMonedaId(Integer monedaId) {
		return repository.findByMonedaId(monedaId).orElseThrow(() -> new MonedaException(monedaId));
	}

}
