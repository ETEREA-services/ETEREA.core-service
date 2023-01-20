/**
 * 
 */
package eterea.api.rest.service.facade;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ArticuloFechaException;
import eterea.api.rest.exception.ArticuloException;
import eterea.api.rest.model.Articulo;
import eterea.api.rest.model.ArticuloFecha;
import eterea.api.rest.model.ProductoArticulo;
import eterea.api.rest.service.ArticuloFechaService;
import eterea.api.rest.service.ArticuloService;
import eterea.api.rest.service.ProductoArticuloService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class PrecioService {

	@Autowired
	private ArticuloFechaService articulofechaservice;

	@Autowired
	private ArticuloService articuloservice;

	@Autowired
	private ProductoArticuloService productoarticuloservice;

	public BigDecimal getUnitPriceByArticuloIdAndFecha(String articuloId, OffsetDateTime fecha) {
		BigDecimal preciounitario = new BigDecimal(0);
		ArticuloFecha articulofecha = null;
		try {
			articulofecha = articulofechaservice.findByUnique(articuloId, fecha);
		} catch (ArticuloFechaException e) {
			log.debug(e.getMessage());
		}
		if (articulofecha == null) {
			Articulo articulo = null;
			try {
				articulo = articuloservice.findByArticuloId(articuloId);
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
		for (ProductoArticulo productoarticulo : productoarticuloservice.findAllByProductoId(productoId)) {
			preciounitario = preciounitario
					.add(getUnitPriceByArticuloIdAndFecha(productoarticulo.getArticuloId(), fecha));
		}
		return preciounitario;
	}
}
