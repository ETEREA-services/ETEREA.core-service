/**
 *
 */
package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Grupo;
import eterea.core.service.model.dto.programadia.VentasPorGrupoDto;
import eterea.core.service.service.GrupoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author daniel
 */
@RestController
@RequestMapping({ "/api/core/grupo", "/grupo" })
public class GrupoController {

	private final GrupoService service;

	public GrupoController(GrupoService service) {
		this.service = service;
	}

	@GetMapping("/")
	public ResponseEntity<List<Grupo>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping("/{grupoId}")
	public ResponseEntity<Grupo> findById(@PathVariable Integer grupoId) {
		return new ResponseEntity<>(service.findById(grupoId), HttpStatus.OK);
	}

	@GetMapping("/ventainternet/{habilitado}")
	public ResponseEntity<List<Grupo>> findAllByVentaInternet(@PathVariable Byte habilitado) {
		return new ResponseEntity<>(service.findAllByVentaInternet(habilitado), HttpStatus.OK);
	}

	@PutMapping("/{grupoId}")
	public ResponseEntity<Grupo> update(@RequestBody Grupo grupo, @PathVariable Integer grupoId) {
		return new ResponseEntity<>(service.update(grupo, grupoId), HttpStatus.OK);
	}

	@GetMapping("/update")
	public ResponseEntity<Grupo> updateByGet(@RequestParam Integer grupoId, @RequestParam String nombre,
			@RequestParam Byte ventainternet) {
		return update(new Grupo(grupoId, nombre, ventainternet), grupoId);
	}

	@GetMapping("/fecha/{fecha}")
	public ResponseEntity<List<Grupo>> findAllByFecha(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return new ResponseEntity<>(service.findAllByVoucherFechaServicio(fecha), HttpStatus.OK);
	}

	@GetMapping("/ventasporgrupo/{fecha}")
	public ResponseEntity<List<VentasPorGrupoDto>> getGruposVendidos(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
		return new ResponseEntity<>(service.getGruposVendidos(fecha), HttpStatus.OK);
	}

	@GetMapping("/contienen-productos")
	public ResponseEntity<List<Grupo>> findAllWithProductos() {
		return new ResponseEntity<>(service.findAllWithProductos(), HttpStatus.OK);
	}
}
