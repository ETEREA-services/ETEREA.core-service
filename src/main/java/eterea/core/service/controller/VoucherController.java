/**
 * 
 */
package eterea.core.service.controller;

import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.service.kotlin.exception.VoucherException;
import eterea.core.service.kotlin.model.Voucher;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.service.VoucherService;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping({"/api/core/voucher", "/voucher"})
public class VoucherController {

	private final VoucherService service;

	public VoucherController(VoucherService service) {
		this.service = service;
	}

	@GetMapping("/today/{user}")
	public ResponseEntity<List<Voucher>> findAllByUserToday(@PathVariable String user) {
		return new ResponseEntity<>(service.findAllByUserToday(user), HttpStatus.OK);
	}

	@GetMapping("/fechaServicio/{fechaServicio}/{soloConfirmados}/{porNombrePax}")
	public ResponseEntity<List<Voucher>> findAllByFecha(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fechaServicio,
			@PathVariable Boolean soloConfirmados, @PathVariable Boolean porNombrePax) {
		return new ResponseEntity<>(
				service.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax), HttpStatus.OK);
	}

	@GetMapping("/byNumeroVoucher/{numeroVoucher}")
	public ResponseEntity<Voucher> findByNumeroVoucher(@PathVariable String numeroVoucher) {
		try {
			return new ResponseEntity<>(service.findByNumeroVoucher(numeroVoucher), HttpStatus.OK);
		} catch (VoucherException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

}
