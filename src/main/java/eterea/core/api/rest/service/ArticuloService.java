/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.ArrayList;
import java.util.List;

import eterea.core.api.rest.exception.ArticuloException;
import eterea.core.api.rest.kotlin.model.Articulo;
import eterea.core.api.rest.kotlin.model.ProductoArticulo;
import eterea.core.api.rest.kotlin.model.VoucherProducto;
import eterea.core.api.rest.kotlin.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloService {

	private final ArticuloRepository repository;

	private final ProductoArticuloService productoArticuloService;

	@Autowired
	public ArticuloService(ArticuloRepository repository, ProductoArticuloService productoArticuloService) {
		this.repository = repository;
		this.productoArticuloService = productoArticuloService;
	}

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

    public List<Articulo> findAllByVoucher(List<VoucherProducto> voucherProductos) {
		var articulos = new ArrayList<Articulo>();
		for (VoucherProducto voucherProducto : voucherProductos) {
			for (ProductoArticulo productoArticulo : productoArticuloService.findAllByProductoId(voucherProducto.getProductoId())) {
				articulos.add(productoArticulo.getArticulo());
			}
		}
		return articulos;
    }
}
