/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.NegocioException;
import eterea.core.api.rest.kotlin.model.Negocio;
import eterea.core.api.rest.kotlin.repository.NegocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class NegocioService {

	@Autowired
	private NegocioRepository repository;

	public Negocio findByNegocioId(Integer negocioId) {
		return repository.findByNegocioId(negocioId).orElseThrow(() -> new NegocioException(negocioId));
	}

}
