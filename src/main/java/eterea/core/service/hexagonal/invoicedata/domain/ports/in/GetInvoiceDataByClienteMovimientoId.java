package eterea.core.service.hexagonal.invoicedata.domain.ports.in;

import eterea.core.service.hexagonal.invoicedata.domain.model.InvoiceData;

public interface GetInvoiceDataByClienteMovimientoId {

    InvoiceData getInvoiceDataByClienteMovimientoId(Long clienteMovimientoId);

}
