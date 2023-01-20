/**
 * 
 */
package eterea.api.rest.controller;

import java.sql.Time;
import java.util.Date;
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
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.LegajoRegistroException;
import eterea.api.rest.model.LegajoRegistro;
import eterea.api.rest.service.LegajoRegistroService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/legajoregistro")
public class LegajoRegistroController {

	@Autowired
	private LegajoRegistroService service;

	@GetMapping("/legajo/{legajoId}")
	public ResponseEntity<List<LegajoRegistro>> findAllByLegajoId(@PathVariable Integer legajoId) {
		return new ResponseEntity<List<LegajoRegistro>>(service.findAllByLegajoId(legajoId), HttpStatus.OK);
	}

	@GetMapping("/last/{legajoId}")
	public ResponseEntity<LegajoRegistro> findLastByLegajoId(@PathVariable Integer legajoId) {
		try {
			return new ResponseEntity<LegajoRegistro>(service.findLastByLegajoId(legajoId), HttpStatus.OK);
		} catch (LegajoRegistroException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/lastday/{legajoId}/{fecha}/{hora}")
	public ResponseEntity<LegajoRegistro> findLastByLegajoIdAndFecha(@PathVariable Integer legajoId,
			@PathVariable @DateTimeFormat(iso = ISO.DATE) Date fecha, @PathVariable Time hora) {
		try {
			return new ResponseEntity<LegajoRegistro>(service.findLastByLegajoIdAndFecha(legajoId, fecha, hora),
					HttpStatus.OK);
		} catch (LegajoRegistroException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

}
