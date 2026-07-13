package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import eterea.core.service.tool.Jsonifier;
import lombok.*;

@Getter
@Builder
public class InvoiceDataResponse {

    ClienteMovimientoInvoiceDataResponse clienteMovimiento;
    RegistroCaeInvoiceDataResponse registroCae;
    ClienteMovimientoInvoiceDataResponse clienteMovimientoAsociado;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
