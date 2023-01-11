/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Valor;
import eterea.api.rest.repository.IValorRepository;

/**
 * @author daniel
 *
 */
@Service
public class ValorService {
	
	@Autowired
	private IValorRepository repository;

	public List<Valor> findAll() {
		return repository.findAll();
	}

}
