/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.repository.ILegajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Legajo;

/**
 * @author daniel
 *
 */
@Service
public class LegajoService {
	
	@Autowired
	private ILegajoRepository repository;

	public List<Legajo> findAll() {
		return repository.findAll();
	}
}
