/**
 *
 */
package eterea.api.rest.controller;

import eterea.api.rest.model.Grupo;
import eterea.api.rest.service.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author daniel
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

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Grupo>> findAllByFecha(@PathVariable String fecha) {
        try {
            return new ResponseEntity<List<Grupo>>(service.findAllByVoucherFechaServicio(fecha), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
