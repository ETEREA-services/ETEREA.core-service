/**
 * 
 */
package eterea.core.api.rest.service.facade;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import eterea.core.api.rest.exception.ArticuloException;
import eterea.core.api.rest.exception.ArticuloFechaException;
import eterea.core.api.rest.kotlin.model.Articulo;
import eterea.core.api.rest.kotlin.model.ArticuloFecha;
import eterea.core.api.rest.kotlin.model.ProductoArticulo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.service.ArticuloFechaService;
import eterea.core.api.rest.service.ArticuloService;
import eterea.core.api.rest.service.ProductoArticuloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class PrecioService {

	private final ArticuloFechaService articuloFechaService;

	private final ArticuloService articuloService;

	private final ProductoArticuloService productoArticuloService;

	@Autowired
	public PrecioService(ArticuloFechaService articuloFechaService, ArticuloService articuloService, ProductoArticuloService productoArticuloService) {
		this.articuloFechaService = articuloFechaService;
		this.articuloService = articuloService;
		this.productoArticuloService = productoArticuloService;
	}

	public BigDecimal getUnitPriceByArticuloIdAndFecha(String articuloId, OffsetDateTime fecha) {
		BigDecimal preciounitario = new BigDecimal(0);
		ArticuloFecha articulofecha = null;
		try {
			articulofecha = articuloFechaService.findByUnique(articuloId, fecha);
		} catch (ArticuloFechaException e) {
			log.debug(e.getMessage());
		}
		if (articulofecha == null) {
			Articulo articulo = null;
			try {
				articulo = articuloService.findByArticuloId(articuloId);
			} catch (ArticuloException e) {
				log.debug(e.getMessage());
			}
			if (articulo != null)
				preciounitario = articulo.getPrecioVentaConIva();
		} else
			preciounitario = articulofecha.getPrecioArs();
		return preciounitario;
	}

	public BigDecimal getUnitPriceByProductoIdAndFecha(Integer productoId, OffsetDateTime fecha) {
		BigDecimal preciounitario = new BigDecimal(0);
		for (ProductoArticulo productoarticulo : productoArticuloService.findAllByProductoId(productoId)) {
			preciounitario = preciounitario
					.add(getUnitPriceByArticuloIdAndFecha(productoarticulo.getArticuloId(), fecha));
		}
		return preciounitario;
	}
}
