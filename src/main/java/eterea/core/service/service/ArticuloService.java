/**
 * 
 */
package eterea.core.service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.exception.ArticuloException;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.ProductoArticulo;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.kotlin.repository.ArticuloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class ArticuloService {

	private final ArticuloRepository repository;

	private final ProductoArticuloService productoArticuloService;

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

	public List<Articulo> findAllByVoucher(List<VoucherProducto> voucherProductos) {
		var articulos = new ArrayList<Articulo>();
		for (VoucherProducto voucherProducto : voucherProductos) {
			for (ProductoArticulo productoArticulo : productoArticuloService.findAllByProductoId(voucherProducto.getProductoId())) {
				articulos.add(productoArticulo.getArticulo());
			}
		}
		return articulos;
	}

	public Articulo findByArticuloId(String articuloId) {
		log.debug("Processing findByArticuloId");
		var articulo = Objects.requireNonNull(repository.findByArticuloId(articuloId)).orElseThrow(() -> new ArticuloException(articuloId));
		logArticulo(articulo);
        return articulo;
	}

	private void logArticulo(Articulo articulo) {
		try {
			log.debug("Articulo -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(articulo));
		} catch (JsonProcessingException e) {
			log.debug("Articulo jsonify error -> {}", e.getMessage());
		}
	}

	public Articulo findByAutoNumerico(Long autoNumerico) {
		log.debug("Processing findByAutoNumerico");
		return Objects.requireNonNull(repository.findByAutoNumericoId(autoNumerico))
				.orElseThrow(() -> new ArticuloException(autoNumerico));
	}

	public Articulo findByMascaraBalanza(String mascaraBalanza) {
		log.debug("Processing findByMascaraBalanza");
		return Objects.requireNonNull(repository.findByMascaraBalanza(mascaraBalanza)).orElseThrow(() -> new ArticuloException(mascaraBalanza));
	}

    public Articulo add(Articulo articulo) {
		log.debug("Processing add");
		articulo.setAutoNumericoId(null);
		articulo = repository.save(articulo);
		logArticulo(articulo);
		return articulo;
    }
}
