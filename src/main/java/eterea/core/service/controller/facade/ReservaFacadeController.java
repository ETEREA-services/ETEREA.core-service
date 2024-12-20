/**
 * 
 */
package eterea.core.service.controller.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.ReservaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/reservafacade", "/reservafacade"})
public class ReservaFacadeController {
	@Autowired
	private ReservaService service;
	
	@GetMapping("/articulos/{clientemovimientoId}")
	public ResponseEntity<Void> completeArticulos(@PathVariable Long clientemovimientoId) {
		service.completeArticulos(clientemovimientoId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
