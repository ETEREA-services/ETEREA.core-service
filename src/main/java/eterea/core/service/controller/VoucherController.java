/**
 *
 */
package eterea.core.service.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import eterea.core.service.kotlin.exception.VoucherException;
import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.VoucherProducto;
import eterea.core.service.service.VoucherService;

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

    @GetMapping("/{voucherId}")
    public ResponseEntity<Voucher> findByVoucherId(@PathVariable Long voucherId) {
        try {
            return new ResponseEntity<>(service.findByVoucherId(voucherId), HttpStatus.OK);
        } catch (VoucherException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/byNumeroVoucherIn")
    public ResponseEntity<List<Voucher>> findAllByNumeroVoucherIn(@RequestParam List<String> numerosVoucher) {
        return new ResponseEntity<>(service.findAllByNumeroVoucherIn(numerosVoucher), HttpStatus.OK);
    }

	@GetMapping("/{voucherId}/productos")
	public ResponseEntity<List<VoucherProducto>> findAllVoucherProductos(@PathVariable Long voucherId) {
		return new ResponseEntity<>(service.findAllVoucherProductos(voucherId), HttpStatus.OK);
	}

}
