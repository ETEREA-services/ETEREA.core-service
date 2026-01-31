package eterea.core.service.hexagonal.invoicedata.infrastructure.web;

import eterea.core.service.hexagonal.invoicedata.application.service.InvoiceDataService;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.InvoiceDataResponse;
import eterea.core.service.hexagonal.invoicedata.infrastructure.mapper.InvoiceDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/invoiceData")
@RequiredArgsConstructor
public class InvoiceDataController {

    private final InvoiceDataService invoiceDataService;
    private final InvoiceDataMapper invoiceDataMapper;

    @GetMapping("/{clienteMovimientoId}")
    public ResponseEntity<InvoiceDataResponse> getInvoiceDatabyClienteMovimientoId(@PathVariable Long clienteMovimientoId) {
        return ResponseEntity.ok(invoiceDataMapper.toResponse(invoiceDataService.getInvoiceData(clienteMovimientoId)));
    }
}
