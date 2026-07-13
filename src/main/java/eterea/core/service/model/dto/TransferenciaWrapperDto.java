package eterea.core.service.model.dto;

import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.tool.Jsonifyable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaWrapperDto implements Jsonifyable {

    private TransferenciaDto transferencia;
    private List<ValorMovimiento> valorMovimientos;
    private List<CuentaMovimiento> cuentaMovimientos;

}
