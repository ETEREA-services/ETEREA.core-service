/**
 * 
 */
package eterea.api.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.api.rest.model.Voucher;
import eterea.api.rest.service.VoucherService;

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
		return new ResponseEntity<List<Voucher>>(service.findAllByUserToday(user), HttpStatus.OK);
	}

}
