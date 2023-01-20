/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ArticuloException;
import eterea.api.rest.model.Articulo;
import eterea.api.rest.repository.IArticuloRepository;

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
