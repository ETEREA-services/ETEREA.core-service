/**
 * 
 */
package eterea.core.api.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.model.Electronico;
import eterea.core.api.rest.service.ElectronicoService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/electronico")
public class ElectronicoController {

	@Autowired
	private ElectronicoService service;

	@GetMapping("/comprobante/{comprobanteId}/{puntoventa}/{numerocomprobante}")
	public ResponseEntity<Electronico> findByComprobante(@PathVariable Integer comprobanteId,
			@PathVariable Integer puntoventa, @PathVariable Long numerocomprobante) {
		return new ResponseEntity<Electronico>(service.findByUnique(comprobanteId, puntoventa, numerocomprobante),
				HttpStatus.OK);
	}
}
