/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.kotlin.model.HabitacionTipo;
import eterea.core.api.rest.repository.HabitacionTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionTipoService {

	@Autowired
	private HabitacionTipoRepository repository;

	public List<HabitacionTipo> findAll() {
		return repository.findAll();
	}

}
