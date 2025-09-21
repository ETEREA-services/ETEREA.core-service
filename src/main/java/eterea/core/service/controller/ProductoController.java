/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.kotlin.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ProductoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/producto", "/producto"})
@RequiredArgsConstructor
public class ProductoController {

	private final ProductoService service;

	@GetMapping("/")
	public ResponseEntity<List<Producto>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/ventainternet/{habilitado}")
	public ResponseEntity<List<Producto>> findAllByVentaInternet(@PathVariable Byte habilitado) {
		return new ResponseEntity<>(service.findAllByVentaInternet(habilitado), HttpStatus.OK);
	}

	@GetMapping("/{productoId}")
	public ResponseEntity<Producto> findById(@PathVariable Integer productoId) {
		return new ResponseEntity<>(service.findById(productoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Producto> add(@RequestBody Producto producto) {
		return new ResponseEntity<>(service.add(producto), HttpStatus.OK);
	}

	@PutMapping("/{productoId}")
	public ResponseEntity<Producto> update(@RequestBody Producto producto, @PathVariable Integer productoId) {
		return new ResponseEntity<>(service.update(producto, productoId), HttpStatus.OK);
	}

}
