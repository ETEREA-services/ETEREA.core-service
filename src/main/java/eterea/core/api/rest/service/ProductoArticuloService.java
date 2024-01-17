/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.ProductoArticuloException;
import eterea.core.api.rest.kotlin.model.ProductoArticulo;
import eterea.core.api.rest.kotlin.repository.ProductoArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ProductoArticuloService {

	private final ProductoArticuloRepository repository;

	@Autowired
	public ProductoArticuloService(ProductoArticuloRepository repository) {
		this.repository = repository;
	}

	public List<ProductoArticulo> findAllByProductoId(Integer productoId) {
		return repository.findAllByProductoId(productoId);
	}

	public ProductoArticulo findByProductoIdAndArticuloId(Integer productoId, String articuloId) {
		return repository.findByProductoIdAndArticuloId(productoId, articuloId).orElseThrow(() -> new ProductoArticuloException(productoId, articuloId));
	}

	public ProductoArticulo add(ProductoArticulo productoarticulo) {
		repository.save(productoarticulo);
		return productoarticulo;
	}

	public void delete(Long productoarticuloId) {
		repository.deleteById(productoarticuloId);
	}

}
