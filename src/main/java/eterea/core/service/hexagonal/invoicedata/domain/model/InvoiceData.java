package eterea.core.service.hexagonal.invoicedata.domain.model;

import eterea.core.service.model.ClienteMovimiento;
import eterea.core.service.model.RegistroCae;
import eterea.core.service.tool.Jsonifier;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceData {

    ClienteMovimiento clienteMovimiento;
    RegistroCae registroCae;
    ClienteMovimiento clienteMovimientoAsociado;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
