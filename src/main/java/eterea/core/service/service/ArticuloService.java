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

	public Articulo update(Articulo newArticulo, String articuloId) {
		log.debug("Processing update");
		return Objects.requireNonNull(repository.findByArticuloId(articuloId)).map(articulo -> {
			articulo = new Articulo.Builder()
					.articuloId(articuloId)
					.negocioId(newArticulo.getNegocioId())
					.descripcion(newArticulo.getDescripcion())
					.leyendaVoucher(newArticulo.getLeyendaVoucher())
					.precioVentaSinIva(newArticulo.getPrecioVentaSinIva())
					.precioVentaConIva(newArticulo.getPrecioVentaConIva())
					.cuentaVentas(newArticulo.getCuentaVentas())
					.cuentaCompras(newArticulo.getCuentaCompras())
					.cuentaGastos(newArticulo.getCuentaGastos())
					.centroStockId(newArticulo.getCentroStockId())
					.rubroId(newArticulo.getRubroId())
					.subRubroId(newArticulo.getSubRubroId())
					.precioCompra(newArticulo.getPrecioCompra())
					.iva105(newArticulo.getIva105())
					.precioCompraNeto(newArticulo.getPrecioCompraNeto())
					.exento(newArticulo.getExento())
					.stockMinimo(newArticulo.getStockMinimo())
					.stockOptimo(newArticulo.getStockOptimo())
					.bloqueoCompras(newArticulo.getBloqueoCompras())
					.bloqueoStock(newArticulo.getBloqueoStock())
					.bloqueoVentas(newArticulo.getBloqueoVentas())
					.unidadMedidaId(newArticulo.getUnidadMedidaId())
					.conDecimales(newArticulo.getConDecimales())
					.ventas(newArticulo.getVentas())
					.compras(newArticulo.getCompras())
					.unidadMedida(newArticulo.getUnidadMedida())
					.conversionId(newArticulo.getConversionId())
					.ventaSinStock(newArticulo.getVentaSinStock())
					.controlaStock(newArticulo.getControlaStock())
					.asientoCostos(newArticulo.getAsientoCostos())
					.mascaraBalanza(newArticulo.getMascaraBalanza())
					.habilitaIngreso(newArticulo.getHabilitaIngreso())
					.comision(newArticulo.getComision())
					.prestadorId(newArticulo.getPrestadorId())
					.autoNumericoId(newArticulo.getAutoNumericoId())
					.build();
			return repository.save(articulo);
        }).orElseThrow(() -> new ArticuloException(articuloId));

	}

	public List<Articulo> findAllByProductoId(int productoId) {
		return repository.findAllByProductoId(productoId);
	}

	public List<Articulo> findAllByProductoIds(List<Integer> productoIds) {
		return repository.findAllByProductoIdIn(productoIds);
	}
}

