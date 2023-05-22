/**
 * 
 */
package eterea.core.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.ProductoArticulo;
import eterea.core.api.rest.service.ProductoArticuloService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/productoarticulo")
public class ProductoArticuloController {

	@Autowired
	private ProductoArticuloService service;

	@GetMapping("/producto/{productoId}")
	public ResponseEntity<List<ProductoArticulo>> findAllByProductoId(@PathVariable Integer productoId) {
		return new ResponseEntity<List<ProductoArticulo>>(service.findAllByProductoId(productoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ProductoArticulo> add(@RequestBody ProductoArticulo productoarticulo) {
		return new ResponseEntity<ProductoArticulo>(service.add(productoarticulo), HttpStatus.OK);
	}

	@DeleteMapping("/{productoarticuloId}")
	public ResponseEntity<Void> delete(@PathVariable Long productoarticuloId) {
		service.delete(productoarticuloId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
