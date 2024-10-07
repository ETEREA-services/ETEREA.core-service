/**
 * 
 */
package eterea.core.service.controller.facade;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.facade.PrecioService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/precio", "/precio"})
public class PrecioController {

	@Autowired
	private PrecioService service;

	@GetMapping("/articulo/{articuloId}/{fecha}")
	public BigDecimal getUnitPriceByArticuloIdAndFecha(@PathVariable String articuloId,
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return service.getUnitPriceByArticuloIdAndFecha(articuloId, fecha);
	}

	@GetMapping("/producto/{productoId}/{fecha}")
	public BigDecimal getUnitPriceByProductoIdAndFecha(@PathVariable Integer productoId,
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return service.getUnitPriceByProductoIdAndFecha(productoId, fecha);
	}
}
