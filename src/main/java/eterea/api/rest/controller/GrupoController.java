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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.Grupo;
import eterea.api.rest.service.GrupoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/grupo")
public class GrupoController {
	@Autowired
	private GrupoService service;
	
	@GetMapping("/")
	public ResponseEntity<List<Grupo>> findAll() {
		return new ResponseEntity<List<Grupo>>(service.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/{grupoId}")
	public ResponseEntity<Grupo> findById(@PathVariable Integer grupoId) {
		return new ResponseEntity<Grupo>(service.findById(grupoId), HttpStatus.OK);
 	}
	
	@GetMapping("/ventainternet/{habilitado}")
	public ResponseEntity<List<Grupo>> findAllByVentaInternet(@PathVariable Byte habilitado) {
		return new ResponseEntity<List<Grupo>>(service.findAllByVentaInternet(habilitado), HttpStatus.OK);
	}
	
	@PutMapping("/{grupoId}")
	public ResponseEntity<Grupo> update(@RequestBody Grupo grupo, @PathVariable Integer grupoId) {
		return new ResponseEntity<Grupo>(service.update(grupo, grupoId), HttpStatus.OK);
	}
	@GetMapping("/update")
	public ResponseEntity<Grupo> updateByGet(@RequestParam Integer grupoId, @RequestParam String nombre, @RequestParam Byte ventainternet) {
		return update(new Grupo(grupoId, nombre, ventainternet), grupoId);
	}
}
