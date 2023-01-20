/**
 * 
 */
package eterea.api.rest.controller.facade;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.api.rest.exception.ProgramaDiaException;
import eterea.api.rest.model.dto.ProgramaDiaDTO;
import eterea.api.rest.service.facade.ProgramaDiaService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/programaDia")
public class ProgramaDiaController {

	@Autowired
	private ProgramaDiaService service;

	@GetMapping("/fechaServicio/{fechaServicio}/{soloConfirmados}/{porNombrePax}")
	public ResponseEntity<ProgramaDiaDTO> findAllByFecha(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fechaServicio,
			@PathVariable Boolean soloConfirmados, @PathVariable Boolean porNombrePax) {
		return new ResponseEntity<ProgramaDiaDTO>(
				service.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax), HttpStatus.OK);
	}

	@GetMapping("/voucher/{voucherId}")
	public ResponseEntity<ProgramaDiaDTO> findByVoucherId(@PathVariable Long voucherId) {
		try {
			return new ResponseEntity<ProgramaDiaDTO>(service.findByVoucherId(voucherId), HttpStatus.OK);
		} catch (ProgramaDiaException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
