/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ParametroNotFoundException;
import eterea.api.rest.model.Parametro;
import eterea.api.rest.repository.IParametroRepository;

/**
 * @author daniel
 *
 */
@Service
public class ParametroService {

	@Autowired
	private IParametroRepository repository;

	public Parametro findLast() {
		return repository.findTopByOrderByParametroIdDesc().orElseThrow(() -> new ParametroNotFoundException());
	}

}
