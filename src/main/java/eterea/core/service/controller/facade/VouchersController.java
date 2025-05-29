package eterea.core.service.controller.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.Voucher;
import eterea.core.service.kotlin.model.dto.ProgramaDiaDto;
import eterea.core.service.model.dto.PregenerarReservaDto;
import eterea.core.service.model.dto.voucher.CreateVoucherDto;
import eterea.core.service.service.facade.VouchersService;

@RestController
@RequestMapping({ "/api/core/vouchers", "/vouchers" })
public class VouchersController {

    private final VouchersService service;

    public VouchersController(VouchersService service) {
        this.service = service;
    }

    @GetMapping("/import/web/one/{orderNumberId}")
    public ResponseEntity<ProgramaDiaDto> importOneFromWeb(@PathVariable Long orderNumberId) {
        return ResponseEntity.ok(service.importOneFromWeb(orderNumberId));
    }

    @GetMapping("/reserva/pre-generate/{orderNumberId}")
    public ResponseEntity<PregenerarReservaDto> preGenerateReserva(@PathVariable Long orderNumberId) {
        return ResponseEntity.ok(service.preGenerarReserva(orderNumberId));
    }

    @PostMapping("/")
    public ResponseEntity<Voucher> createVoucher(@RequestBody CreateVoucherDto createVoucherDto) {
        return ResponseEntity.ok(service.createVoucher(createVoucherDto));
    }
}
