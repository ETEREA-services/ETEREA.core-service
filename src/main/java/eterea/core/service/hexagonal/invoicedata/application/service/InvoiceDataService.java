package eterea.core.service.hexagonal.invoicedata.application.service;

import eterea.core.service.hexagonal.invoicedata.domain.model.InvoiceData;
import eterea.core.service.hexagonal.invoicedata.domain.ports.in.GetInvoiceDataByClienteMovimientoId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class InvoiceDataService {

    private final GetInvoiceDataByClienteMovimientoId getInvoiceDataByClienteMovimientoId;

    public InvoiceData getInvoiceData(Long clienteMovimientoId) {
        return getInvoiceDataByClienteMovimientoId.getInvoiceDataByClienteMovimientoId(clienteMovimientoId);
    }

}
