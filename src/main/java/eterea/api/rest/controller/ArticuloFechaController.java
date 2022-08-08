/**
 * 
 */
package eterea.api.rest.controller;

import java.time.OffsetDateTime;

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

import eterea.api.rest.model.ArticuloFecha;
import eterea.api.rest.service.ArticuloFechaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/articulofecha")
public class ArticuloFechaController {
	@Autowired
	private ArticuloFechaService service;

	@GetMapping("/unique/{articuloId}/{fecha}")
	public ResponseEntity<ArticuloFecha> getByUnique(@PathVariable String articuloId,
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return new ResponseEntity<ArticuloFecha>(service.findByUnique(articuloId, fecha), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ArticuloFecha> add(@RequestBody ArticuloFecha articulofecha) {
		return new ResponseEntity<ArticuloFecha>(service.add(articulofecha), HttpStatus.OK);
	}

	@PutMapping("/{articulofechaId}")
	public ResponseEntity<ArticuloFecha> update(@RequestBody ArticuloFecha articulofecha,
			@PathVariable Long articulofechaId) {
		return new ResponseEntity<ArticuloFecha>(service.update(articulofecha, articulofechaId), HttpStatus.OK);
	}

}
