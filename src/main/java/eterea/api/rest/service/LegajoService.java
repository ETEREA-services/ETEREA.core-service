/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Legajo;
import eterea.api.rest.repository.ILegajoRepository;

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
