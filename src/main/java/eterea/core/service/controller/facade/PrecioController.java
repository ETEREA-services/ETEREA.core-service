/**
 * 
 */
package eterea.core.service.controller.facade;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import eterea.core.service.kotlin.model.dto.PriceDto;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import eterea.core.service.service.facade.PrecioService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/precio", "/precio"})
public class PrecioController {

	private final PrecioService service;

	public PrecioController(PrecioService service) {
		this.service = service;
	}

	@GetMapping("/articulo/{articuloId}/{fecha}")
	public ResponseEntity<BigDecimal> getUnitPriceByArticuloIdAndFecha(@PathVariable String articuloId,
																	  @PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return ResponseEntity.ok(service.getUnitPriceByArticuloIdAndFecha(articuloId, fecha));
	}

	@GetMapping("/producto/{productoId}/{fecha}")
	public ResponseEntity<BigDecimal> getUnitPriceByProductoIdAndFecha(@PathVariable Integer productoId,
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return ResponseEntity.ok(service.getUnitPriceByProductoIdAndFecha(productoId, fecha));
	}

	@PostMapping("/set/periodo")
	public ResponseEntity<Boolean> setUnitPriceByPeriod(@RequestBody PriceDto priceDto) {
		return ResponseEntity.ok(service.setUnitPriceByPeriod(priceDto));
	}

}
