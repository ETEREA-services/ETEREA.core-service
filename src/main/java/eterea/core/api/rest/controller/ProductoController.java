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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.Producto;
import eterea.core.api.rest.service.ProductoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	private ProductoService service;

	@GetMapping("/")
	public ResponseEntity<List<Producto>> findAll() {
		return new ResponseEntity<List<Producto>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/ventainternet/{habilitado}")
	public ResponseEntity<List<Producto>> findAllByVentaInternet(@PathVariable Byte habilitado) {
		return new ResponseEntity<List<Producto>>(service.findAllByVentaInternet(habilitado), HttpStatus.OK);
	}

	@GetMapping("/{productoId}")
	public ResponseEntity<Producto> findById(@PathVariable Integer productoId) {
		return new ResponseEntity<Producto>(service.findById(productoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Producto> add(@RequestBody Producto producto) {
		return new ResponseEntity<Producto>(service.add(producto), HttpStatus.OK);
	}

	@PutMapping("/{productoId}")
	public ResponseEntity<Producto> update(@RequestBody Producto producto, @PathVariable Integer productoId) {
		return new ResponseEntity<Producto>(service.update(producto, productoId), HttpStatus.OK);
	}

}
