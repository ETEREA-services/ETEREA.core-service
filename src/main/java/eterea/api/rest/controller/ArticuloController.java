/**
 * 
 */
package eterea.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.ArticuloNotFoundException;
import eterea.api.rest.model.Articulo;
import eterea.api.rest.model.dto.ArticuloPesoDTO;
import eterea.api.rest.model.view.ArticuloSearch;
import eterea.api.rest.service.ArticuloService;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {

	@Autowired
	private ArticuloService service;

	@GetMapping("/")
	public ResponseEntity<List<Articulo>> findAll() {
		return new ResponseEntity<List<Articulo>>(service.findAll(), HttpStatus.OK);
	}

	@PostMapping("/search")
	public ResponseEntity<List<ArticuloSearch>> findAllByStrings(@RequestBody List<String> conditions) {
		return new ResponseEntity<List<ArticuloSearch>>(service.findAllByStrings(conditions), HttpStatus.OK);
	}

	@PostMapping("/search/venta")
	public ResponseEntity<List<ArticuloSearch>> findAllVentaByStrings(@RequestBody List<String> conditions) {
		return new ResponseEntity<List<ArticuloSearch>>(service.findAllVentaByStrings(conditions), HttpStatus.OK);
	}

	@GetMapping("/{articuloId}")
	public ResponseEntity<Articulo> findByArticuloId(@PathVariable String articuloId) {
		try {
			return new ResponseEntity<Articulo>(service.findByArticuloId(articuloId), HttpStatus.OK);
		} catch (ArticuloNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/unique/{uniqueId}")
	public ResponseEntity<Articulo> findByUniqueId(@PathVariable Long uniqueId) {
		try {
			return new ResponseEntity<Articulo>(service.findByUniqueId(uniqueId), HttpStatus.OK);
		} catch (ArticuloNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@GetMapping("/peso/{codigo}")
	public ResponseEntity<ArticuloPesoDTO> findArticuloAndPeso(@PathVariable String codigo) {
		return new ResponseEntity<ArticuloPesoDTO>(service.findArticuloAndPeso(codigo), HttpStatus.OK);
	}

}
