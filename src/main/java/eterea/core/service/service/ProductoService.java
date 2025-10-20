/**
 * 
 */
package eterea.core.service.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.core.service.exception.ProductoException;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.kotlin.model.ProductoArticulo;
import eterea.core.service.model.dto.ProductoDetailsDto;
import eterea.core.service.repository.ProductoRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProductoService {

	private final ProductoRepository repository;
	private final ProductoArticuloService productoArticuloService;

	public ProductoService(ProductoRepository repository, ProductoArticuloService productoArticuloService) {
		this.repository = repository;
		this.productoArticuloService = productoArticuloService;
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

	public List<Producto> findAllByGrupoId(Integer grupoId) {
		return repository.findAllByGrupoId(grupoId);
	}

	public List<Producto> findAllByVoucherId(Long voucherId) {
		return repository.findAllByVoucherId(voucherId);
	}

	public List<Producto> findAllByIds(List<Integer> productoIds) {
		return repository.findAllByProductoIdIn(productoIds);
	}

	public List<Producto> findByExactArticuloIds(List<String> articuloIds) {
		return repository.findByExactArticuloIds(articuloIds, articuloIds.size());
	}

	public List<Producto> findAllHabilitados() {
		return repository.findAllByDeshabilitado(0);
	}

	public List<ProductoDetailsDto> findAllByIdsWithArticulos(List<Integer> productoIds) {
		List<Producto> productos = repository.findAllByProductoIdIn(productoIds);

		List<ProductoArticulo> productoArticulos = productoArticuloService.findAllByProductoIdIn(productoIds);

		// Map<ProductoId, List<Articulo>>
		Map<Integer, List<Articulo>> articulosByProductoId = productoArticulos.stream()
				.filter(pa -> pa != null && pa.getArticulo() != null)
				.collect(Collectors.groupingBy(
						ProductoArticulo::getProductoId,
						Collectors.mapping(ProductoArticulo::getArticulo, Collectors.toList())));

		return productos.stream()
				.filter(p -> p != null && p.getProductoId() != null)
				.map(producto -> new ProductoDetailsDto(
						producto.getProductoId(),
						producto.getNombre(),
						producto.getObservaciones(),
						producto.getDeshabilitado(),
						producto.getTraslado(),
						producto.getPuntoEncuentro(),
						producto.getVentaMostrador(),
						producto.getVentaInternet(),
						articulosByProductoId.getOrDefault(producto.getProductoId(), new ArrayList<>())))
				.collect(Collectors.toList());
	}

	public List<ProductoDetailsDto> findAllWithArticulos(boolean includeDeshabilitados) {
		List<Producto> productos = includeDeshabilitados
				? repository.findAll()
				: repository.findAllByDeshabilitado(0);
		List<ProductoArticulo> productoArticulos = productoArticuloService.findAllByProductoIdIn(productos.stream()
				.map(Producto::getProductoId)
				.toList());
		Map<Integer, List<Articulo>> articulosByProductoId = productoArticulos.stream()
				.filter(pa -> pa != null && pa.getArticulo() != null)
				.collect(Collectors.groupingBy(
						ProductoArticulo::getProductoId,
						Collectors.mapping(ProductoArticulo::getArticulo, Collectors.toList())));

		return productos.stream()
				.filter(p -> p != null && p.getProductoId() != null)
				.map(producto -> new ProductoDetailsDto(
						producto.getProductoId(),
						producto.getNombre(),
						producto.getObservaciones(),
						producto.getDeshabilitado(),
						producto.getTraslado(),
						producto.getPuntoEncuentro(),
						producto.getVentaMostrador(),
						producto.getVentaInternet(),
						articulosByProductoId.getOrDefault(producto.getProductoId(), new ArrayList<>())))
				.collect(Collectors.toList());
	}

}
