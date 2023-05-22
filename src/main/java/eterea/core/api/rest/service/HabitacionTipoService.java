/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.repository.IHabitacionTipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.HabitacionTipo;

/**
 * @author daniel
 *
 */
@Service
public class HabitacionTipoService {

	@Autowired
	private IHabitacionTipoRepository repository;

	public List<HabitacionTipo> findAll() {
		return repository.findAll();
	}

}
