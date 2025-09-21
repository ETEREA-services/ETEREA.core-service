/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.ProductoArticulo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ProductoArticuloService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/productoarticulo", "/productoarticulo"})
@RequiredArgsConstructor
public class ProductoArticuloController {

	private final ProductoArticuloService service;

	@GetMapping("/producto/{productoId}")
	public ResponseEntity<List<ProductoArticulo>> findAllByProductoId(@PathVariable Integer productoId) {
		return new ResponseEntity<>(service.findAllByProductoId(productoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<ProductoArticulo> add(@RequestBody ProductoArticulo productoarticulo) {
		return new ResponseEntity<>(service.add(productoarticulo), HttpStatus.OK);
	}

	@DeleteMapping("/{productoarticuloId}")
	public ResponseEntity<Void> delete(@PathVariable Long productoarticuloId) {
		service.delete(productoarticuloId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
