/**
 * 
 */
package eterea.core.service.controller.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.dto.ImpresionFiscalDto;
import eterea.core.service.service.facade.ImpresionFiscalService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/impresionFiscal", "/impresionFiscal"})
@RequiredArgsConstructor
public class ImpresionFiscalController {

	private final ImpresionFiscalService service;

	@GetMapping("/fiscal/{ipAddress}/{hWnd}/{clienteId}/{comprobanteId}/{comprobanteOrigenId}")
	public ResponseEntity<ImpresionFiscalDto> getData(@PathVariable String ipAddress,
													  @PathVariable Long hWnd,
                                                      @PathVariable Long clienteId,
													  @PathVariable Integer comprobanteId,
													  @PathVariable Long comprobanteOrigenId) {
        return ResponseEntity.ok(service.getData(ipAddress, hWnd, clienteId, comprobanteId, comprobanteOrigenId));
	}

	@GetMapping("/fiscalPrevio/{clienteMovimientoPrevioId}/{comprobanteId}/{comprobanteOrigenId}")
	public ResponseEntity<ImpresionFiscalDto> getDataPrevio(@PathVariable Long clienteMovimientoPrevioId,
                                                            @PathVariable Integer comprobanteId,
															@PathVariable Long comprobanteOrigenId) {
        return ResponseEntity.ok(service.getDataPrevio(clienteMovimientoPrevioId, comprobanteId, comprobanteOrigenId));
	}

}
