/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.model.Legajo;
import eterea.core.service.repository.LegajoRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class LegajoService {
	
	private final LegajoRepository repository;

	public LegajoService(LegajoRepository repository) {
		this.repository = repository;
	}

	public List<Legajo> findAll() {
		return repository.findAll();
	}
}
