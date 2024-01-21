/**
 * 
 */
package eterea.core.api.rest.controller.facade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import eterea.core.api.rest.service.facade.MakeFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.facade.FacturaPdfService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/makefactura")
public class MakeFacturaController {

	private final MakeFacturaService service;

	private final FacturaPdfService facturaPdfService;

	@Autowired
	public MakeFacturaController(MakeFacturaService service, FacturaPdfService facturaPdfService) {
		this.service = service;
		this.facturaPdfService = facturaPdfService;
	}

	@GetMapping("/pdf/{clientemovimientoId}")
	public ResponseEntity<Resource> makePdf(@PathVariable Long clientemovimientoId) throws FileNotFoundException {
		String filename = facturaPdfService.generatePdf(clientemovimientoId);
		File file = new File(filename);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura.pdf");
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return ResponseEntity.ok().headers(headers).contentLength(file.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@GetMapping("/facturaReserva/{reservaId}/{comprobanteId}")
	public ResponseEntity<?> facturaReserva(@PathVariable Long reservaId, @PathVariable Integer comprobanteId) {
		service.facturaReserva(reservaId, comprobanteId);
		return (ResponseEntity<?>) ResponseEntity.ok();
	}

}
