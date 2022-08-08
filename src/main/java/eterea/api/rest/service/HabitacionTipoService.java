/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.HabitacionTipo;
import eterea.api.rest.repository.IHabitacionTipoRepository;

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
