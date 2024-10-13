/**
 * 
 */
package eterea.core.service.controller;

import java.time.OffsetDateTime;

import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.model.ArticuloFecha;
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

import eterea.core.service.service.ArticuloFechaService;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/articulofecha", "/articulofecha"})
public class ArticuloFechaController {

	private final ArticuloFechaService service;

	public ArticuloFechaController(ArticuloFechaService service) {
		this.service = service;
	}

	@GetMapping("/unique/{articuloId}/{fecha}")
	public ResponseEntity<ArticuloFecha> getByUnique(@PathVariable String articuloId,
													 @PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		try {
			return new ResponseEntity<>(service.findByUnique(articuloId, fecha), HttpStatus.OK);
		} catch (ArticuloFechaException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
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
