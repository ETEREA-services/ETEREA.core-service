/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.MonedaNotFoundException;
import eterea.api.rest.model.Moneda;
import eterea.api.rest.repository.IMonedaRepository;

/**
 * @author daniel
 *
 */
@Service
public class MonedaService {

	@Autowired
	private IMonedaRepository repository;

	public Moneda findByMonedaId(Integer monedaId) {
		return repository.findByMonedaId(monedaId).orElseThrow(() -> new MonedaNotFoundException(monedaId));
	}

}
