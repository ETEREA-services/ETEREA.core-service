/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Grupo;
import eterea.api.rest.repository.IGrupoRepository;

/**
 * @author daniel
 *
 */
@Service
public class GrupoService {
	@Autowired
	private IGrupoRepository repository;

	public List<Grupo> findAll() {
		return repository.findAll(Sort.by("nombre").ascending());
	}
	
	public Grupo findById(Integer grupoId) {
		return repository.findById(grupoId).orElseThrow(() -> new GrupoNotFoundException(grupoId));
	}

	public List<Grupo> findAllByVentaInternet(Byte habilitado) {
		return repository.findAllByVentainternet(habilitado, Sort.by("nombre").ascending());
	}

	public Grupo update(Grupo newgrupo, Integer grupoId) {
		return repository.findById(grupoId).map(grupo -> {
			grupo.setNombre(newgrupo.getNombre());
			grupo.setVentainternet(newgrupo.getVentainternet());
			return repository.save(grupo);
		}).orElseThrow(() -> new GrupoNotFoundException(grupoId));
	}

}
