/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.model.HabitacionTipo;
import eterea.core.service.repository.HabitacionTipoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class HabitacionTipoService {

	private final HabitacionTipoRepository repository;

	public List<HabitacionTipo> findAll() {
		return repository.findAll();
	}

}
