/**
 * 
 */
package eterea.core.service.service.facade;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.service.exception.ArticuloException;
import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.kotlin.model.ArticuloFecha;
import eterea.core.service.kotlin.model.ProductoArticulo;
import eterea.core.service.kotlin.model.dto.PriceDto;
import org.springframework.stereotype.Service;

import eterea.core.service.service.ArticuloFechaService;
import eterea.core.service.service.ArticuloService;
import eterea.core.service.service.ProductoArticuloService;
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

	public Boolean setUnitPriceByPeriod(PriceDto priceDto) {
		List<ArticuloFecha> articulofechas = articuloFechaService.findAllByArticuloIdAndPeriodo(
				priceDto.getArticuloId(), priceDto.getFechaInicio(), priceDto.getFechaFin());
		return false;
	}
}
