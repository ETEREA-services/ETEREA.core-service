/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.ProductoArticuloException;
import eterea.core.service.kotlin.model.ProductoArticulo;
import eterea.core.service.kotlin.repository.ProductoArticuloRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ProductoArticuloService {

	private final ProductoArticuloRepository repository;

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

	public List<ProductoArticulo> findAllByProductoIdIn(List<Integer> productoIds) {
		return repository.findAllByProductoIdIn(productoIds);
	}

}
