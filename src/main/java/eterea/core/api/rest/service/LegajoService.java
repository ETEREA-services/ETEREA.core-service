/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.kotlin.model.Legajo;
import eterea.core.api.rest.repository.LegajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class LegajoService {
	
	@Autowired
	private LegajoRepository repository;

	public List<Legajo> findAll() {
		return repository.findAll();
	}
}
