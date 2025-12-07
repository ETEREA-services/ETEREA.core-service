package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProveedorMovimientoNetoAjusteRequest {

    private Long proveedorMovimientoId;
    private BigDecimal netoAjustado;
    private BigDecimal montoIva21Ajustado;
    private BigDecimal montoIva105Ajustado;
    private BigDecimal montoIva27Ajustado;

}
