/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ArticuloCentro;
import eterea.api.rest.repository.IArticuloCentroRepository;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloCentroService {

	@Autowired
	private IArticuloCentroRepository repository;

	public List<ArticuloCentro> findAll() {
		return repository.findAll();
	}

}
