/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ProductoException;
import eterea.api.rest.model.Producto;
import eterea.api.rest.repository.IProductoRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProductoService {
	@Autowired
	private IProductoRepository repository;

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
		return repository.findById(productoId).map(producto -> {
			producto.setNombre(newproducto.getNombre());
			producto.setObservaciones(newproducto.getObservaciones());
			producto.setDeshabilitado(newproducto.getDeshabilitado());
			producto.setTraslado(newproducto.getTraslado());
			producto.setPuntoencuentro(newproducto.getPuntoencuentro());
			producto.setVentamostrador(newproducto.getVentamostrador());
			producto.setVentainternet(newproducto.getVentainternet());
			return repository.save(producto);
		}).orElseThrow(() -> new ProductoException(productoId));
	}
}
