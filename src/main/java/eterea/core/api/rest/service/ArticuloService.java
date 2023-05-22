/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.ArticuloException;
import eterea.core.api.rest.repository.IArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Articulo;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloService {

	@Autowired
	private IArticuloRepository repository;

	public List<Articulo> findAll() {
		return repository.findAll();
	}

	public List<Articulo> findAllBySearch(String chain) {
		return repository.findTop50ByDescripcionLikeOrderByDescripcion("%" + chain + "%");
	}

	public Articulo findByArticuloId(String articuloId) {
		return repository.findByArticuloId(articuloId).orElseThrow(() -> new ArticuloException(articuloId));
	}

	public Articulo findByAutoNumericoId(Long autoNumericoId) {
		return repository.findByAutoNumericoId(autoNumericoId)
				.orElseThrow(() -> new ArticuloException(autoNumericoId));
	}

}
