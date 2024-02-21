/**
 * 
 */
package eterea.core.api.rest.controller;

import java.time.OffsetDateTime;

import eterea.core.api.rest.kotlin.model.ArticuloFecha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.ArticuloFechaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/articulofecha")
public class ArticuloFechaController {

	private final ArticuloFechaService service;

	@Autowired
	public ArticuloFechaController(ArticuloFechaService service) {
		this.service = service;
	}

	@GetMapping("/unique/{articuloId}/{fecha}")
	public ResponseEntity<ArticuloFecha> getByUnique(@PathVariable String articuloId,
													 @PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return new ResponseEntity<>(service.findByUnique(articuloId, fecha), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ArticuloFecha> add(@RequestBody ArticuloFecha articulofecha) {
		return new ResponseEntity<>(service.add(articulofecha), HttpStatus.OK);
	}

	@PutMapping("/{articuloFechaId}")
	public ResponseEntity<ArticuloFecha> update(@RequestBody ArticuloFecha articulofecha,
			@PathVariable Long articuloFechaId) {
		return new ResponseEntity<>(service.update(articulofecha, articuloFechaId), HttpStatus.OK);
	}

}
