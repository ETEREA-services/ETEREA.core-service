/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.ProductoException;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ProductoService {

	private final ProductoRepository repository;

	public ProductoService(ProductoRepository repository) {
		this.repository = repository;
	}

	public List<Producto> findAll() {
		return repository.findAll(Sort.by("nombre").ascending());
	}

	public List<Producto> findAllByVentaInternet(Byte habilitado) {
		return repository.findAllByVentaInternet(habilitado, Sort.by("nombre").ascending());
	}

	public Producto findById(Integer productoId) {
		return repository.findById(productoId).orElseThrow(() -> new ProductoException(productoId));
	}

	public Producto add(Producto producto) {
		repository.save(producto);
		return producto;
	}

	public Producto update(Producto newproducto, Integer productoId) {
		return repository.findByProductoId(productoId).map(producto -> {
			producto.setNombre(newproducto.getNombre());
			producto.setObservaciones(newproducto.getObservaciones());
			producto.setDeshabilitado(newproducto.getDeshabilitado());
			producto.setTraslado(newproducto.getTraslado());
			producto.setPuntoEncuentro(newproducto.getPuntoEncuentro());
			producto.setVentaMostrador(newproducto.getVentaMostrador());
			producto.setVentaInternet(newproducto.getVentaInternet());
			return repository.save(producto);
		}).orElseThrow(() -> new ProductoException(productoId));
	}
}
