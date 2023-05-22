/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.NegocioException;
import eterea.core.api.rest.repository.INegocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Negocio;

/**
 * @author daniel
 *
 */
@Service
public class NegocioService {

	@Autowired
	private INegocioRepository repository;

	public Negocio findByNegocioId(Integer negocioId) {
		return repository.findByNegocioId(negocioId).orElseThrow(() -> new NegocioException(negocioId));
	}

}
