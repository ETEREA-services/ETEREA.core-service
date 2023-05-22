/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.Articulo;
import eterea.core.api.rest.service.ArticuloService;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {

	@Autowired
	private ArticuloService service;

	@GetMapping("/")
	public ResponseEntity<List<Articulo>> findAll() {
		return new ResponseEntity<List<Articulo>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/search/{chain}")
	public ResponseEntity<List<Articulo>> findAllBySearch(@PathVariable String chain) {
		return new ResponseEntity<List<Articulo>>(service.findAllBySearch(chain), HttpStatus.OK);
	}

	@GetMapping("/{articuloId}")
	public ResponseEntity<Articulo> findById(@PathVariable String articuloId) {
		return new ResponseEntity<Articulo>(service.findByArticuloId(articuloId), HttpStatus.OK);
	}

	@GetMapping("/autonumerico/{autonumerico}")
	public ResponseEntity<Articulo> findByAutonumerico(@PathVariable Long autonumerico) {
		return new ResponseEntity<Articulo>(service.findByAutoNumericoId(autonumerico), HttpStatus.OK);
	}
}
