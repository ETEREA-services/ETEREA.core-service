/**
 * 
 */
package eterea.core.service.controller;

import java.util.List;

import eterea.core.service.exception.LegajoException;
import eterea.core.service.model.Legajo;
import eterea.core.service.model.view.LegajoSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.LegajoService;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/legajo", "/legajo"})
@RequiredArgsConstructor
public class LegajoController {

	private final LegajoService service;

	@GetMapping("/")
	public ResponseEntity<List<Legajo>> findAll() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

    @GetMapping("/{legajoId}")
    public ResponseEntity<Legajo> findByLegajoId(@PathVariable Integer legajoId) {
        try {
            return ResponseEntity.ok(service.findByLegajoId(legajoId));
        } catch (LegajoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

	@GetMapping("/search/{chain}")
	public ResponseEntity<List<LegajoSearch>> findAllBySearch(@PathVariable String chain) {
		return new ResponseEntity<>(service.findAllBySearch(chain), HttpStatus.OK);
	}

}
