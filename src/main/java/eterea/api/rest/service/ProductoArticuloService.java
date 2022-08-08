/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ProductoArticulo;
import eterea.api.rest.repository.IProductoArticuloRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProductoArticuloService {
	@Autowired
	private IProductoArticuloRepository repository;

	public List<ProductoArticulo> findAllByProductoId(Integer productoId) {
		return repository.findAllByProductoId(productoId);
	}

	public ProductoArticulo add(ProductoArticulo productoarticulo) {
		repository.save(productoarticulo);
		return productoarticulo;
	}

	public void delete(Long productoarticuloId) {
		repository.deleteById(productoarticuloId);
	}
}
