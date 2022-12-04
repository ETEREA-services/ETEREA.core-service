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

import eterea.api.rest.model.dto.ImpresionFiscalDTO;
import eterea.api.rest.service.facade.ImpresionFiscalService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/impresionFiscal")
public class ImpresionFiscalController {

	@Autowired
	private ImpresionFiscalService service;

	@GetMapping("/fiscal/{ipAddress}/{hWnd}/{clienteId}/{comprobanteId}")
	public ResponseEntity<ImpresionFiscalDTO> getData(@PathVariable String ipAddress, @PathVariable Long hWnd,
			@PathVariable Long clienteId, @PathVariable Integer comprobanteId) {
		return new ResponseEntity<ImpresionFiscalDTO>(service.getData(ipAddress, hWnd, clienteId, comprobanteId),
				HttpStatus.OK);
	}

}
