package eterea.core.service.hexagonal.reservas.voucher.infrastructure.web.controller;

import eterea.core.service.hexagonal.reservas.voucher.application.exception.VoucherException;
import eterea.core.service.hexagonal.reservas.voucher.application.service.VoucherService;
import eterea.core.service.hexagonal.reservas.voucher.infrastructure.web.dto.VoucherResponse;
import eterea.core.service.hexagonal.reservas.voucher.infrastructure.web.mapper.VoucherDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping({"/api/core/voucher", "/voucher"})
@RequiredArgsConstructor
public class VoucherController {

    private final VoucherService service;
    private final VoucherDtoMapper voucherDtoMapper;

    @GetMapping("/today/{user}")
    public ResponseEntity<List<VoucherResponse>> findAllByUserToday(@PathVariable String user) {
        return new ResponseEntity<>(
                service.findAllByUserToday(user).stream().map(voucherDtoMapper::toResponse).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/fechaServicio/{fechaServicio}/{soloConfirmados}/{porNombrePax}")
    public ResponseEntity<List<VoucherResponse>> findAllByFecha(
            @PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fechaServicio,
            @PathVariable Boolean soloConfirmados, @PathVariable Boolean porNombrePax) {
        return new ResponseEntity<>(
                service.findAllByFechaServicio(fechaServicio, soloConfirmados, porNombrePax).stream()
                        .map(voucherDtoMapper::toResponse).toList(),
                HttpStatus.OK);
    }

    @GetMapping("/byNumeroVoucher/{numeroVoucher}")
    public ResponseEntity<VoucherResponse> findByNumeroVoucher(@PathVariable String numeroVoucher) {
        try {
            return new ResponseEntity<>(voucherDtoMapper.toResponse(service.findByNumeroVoucher(numeroVoucher)),
                    HttpStatus.OK);
        } catch (VoucherException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{voucherId}")
    public ResponseEntity<VoucherResponse> findByVoucherId(@PathVariable Long voucherId) {
        try {
            return new ResponseEntity<>(voucherDtoMapper.toResponse(service.findByVoucherId(voucherId)),
                    HttpStatus.OK);
        } catch (VoucherException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
