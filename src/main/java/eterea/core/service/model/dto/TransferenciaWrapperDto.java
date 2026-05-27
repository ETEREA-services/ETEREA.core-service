package eterea.core.service.model.dto;

import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.model.CuentaMovimiento;
import eterea.core.service.tool.Jsonifier;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaWrapperDto {

    private TransferenciaDto transferencia;
    private List<ValorMovimiento> valorMovimientos;
    private List<CuentaMovimiento> cuentaMovimientos;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
