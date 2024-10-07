/**
 * 
 */
package eterea.core.service.controller.view;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.view.ProductoCantidad;
import eterea.core.service.service.view.ProductoCantidadService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/productocantidad", "/productocantidad"})
public class ProductoCantidadController {

	@Autowired
	private ProductoCantidadService service;

	@GetMapping("/fechaServicio/{fechaServicio}")
	public ResponseEntity<List<ProductoCantidad>> findAllByFechaServicio(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fechaServicio) {
		return new ResponseEntity<List<ProductoCantidad>>(service.findAllByFechaServicio(fechaServicio), HttpStatus.OK);
	}
}
