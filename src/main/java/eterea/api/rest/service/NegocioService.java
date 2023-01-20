/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.NegocioException;
import eterea.api.rest.model.Negocio;
import eterea.api.rest.repository.INegocioRepository;

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
