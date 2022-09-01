/**
 * 
 */
package eterea.api.rest.controller.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.service.facade.ReservaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/reservafacade")
public class ReservaFacadeController {
	@Autowired
	private ReservaService service;
	
	@GetMapping("/articulos/{clientemovimientoId}")
	public ResponseEntity<Void> completeArticulos(@PathVariable Long clientemovimientoId) {
		service.completeArticulos(clientemovimientoId);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
