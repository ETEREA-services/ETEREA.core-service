/**
 * 
 */
package eterea.core.api.rest.controller;

import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.api.rest.kotlin.model.Voucher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.api.rest.service.VoucherService;

/**
 * @author daniel
 *
 */
@RestController
@RequestMapping("/voucher")
public class VoucherController {

	@Autowired
	private VoucherService service;

	@GetMapping("/today/{user}")
	public ResponseEntity<List<Voucher>> findAllByUserToday(@PathVariable String user) {
		return new ResponseEntity<>(service.findAllByUserToday(user), HttpStatus.OK);
	}

	@GetMapping("/fechaServicio/{fechaServicio}/{soloConfirmados}/{porNombrePax}")
	public ResponseEntity<List<Voucher>> findAllByFecha(
			@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fechaServicio,
			@PathVariable Boolean soloConfirmados, @PathVariable Boolean porNombrePax) {
		return new ResponseEntity<List<Voucher>>(
				service.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax), HttpStatus.OK);
	}

}
