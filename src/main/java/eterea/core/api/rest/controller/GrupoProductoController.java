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

import eterea.core.api.rest.model.GrupoProducto;
import eterea.core.api.rest.service.GrupoProductoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/grupoproducto")
public class GrupoProductoController {

	@Autowired
	private GrupoProductoService service;

	@GetMapping("/")
	public ResponseEntity<List<GrupoProducto>> findAll() {
		return new ResponseEntity<List<GrupoProducto>>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/producto/{productoId}")
	public ResponseEntity<List<GrupoProducto>> findByProductoId(@PathVariable Integer productoId) {
		return new ResponseEntity<List<GrupoProducto>>(service.findByProductoId(productoId), HttpStatus.OK);
	}

	@GetMapping("/unique/{grupoId}/{productoId}")
	public ResponseEntity<GrupoProducto> findByUnique(@PathVariable Integer grupoId, @PathVariable Integer productoId) {
		return new ResponseEntity<GrupoProducto>(service.findByUnique(grupoId, productoId), HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<GrupoProducto> add(@RequestBody GrupoProducto grupoproducto) {
		return new ResponseEntity<GrupoProducto>(service.add(grupoproducto), HttpStatus.OK);
	}

	@PostMapping("/{grupoId}/{productoId}")
	public ResponseEntity<GrupoProducto> update(@RequestBody GrupoProducto grupoproducto, @PathVariable Integer grupoId,
			@PathVariable Integer productoId) {
		return new ResponseEntity<GrupoProducto>(service.update(grupoproducto, grupoId, productoId), HttpStatus.OK);
	}

	@DeleteMapping("/{grupoproductoId}")
	public ResponseEntity<Void> deleteById(@PathVariable Long grupoproductoId) {
		service.deleteById(grupoproductoId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
