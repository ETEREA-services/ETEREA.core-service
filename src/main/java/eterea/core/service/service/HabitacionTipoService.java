/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.model.HabitacionTipo;
import eterea.core.service.repository.HabitacionTipoRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionTipoService {

	private final HabitacionTipoRepository repository;

	public HabitacionTipoService(HabitacionTipoRepository repository) {
		this.repository = repository;
	}

	public List<HabitacionTipo> findAll() {
		return repository.findAll();
	}

	public HabitacionTipo findById(Integer id) {
		return repository.findById(id).orElse(null);
	}
}
