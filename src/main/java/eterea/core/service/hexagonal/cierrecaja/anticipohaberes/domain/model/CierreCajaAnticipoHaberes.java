package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CierreCajaAnticipoHaberes {

    private Long cierreCajaId;
    private Integer legajoId;
    private OffsetDateTime fecha;
    private BigDecimal importe;
    private Long userId;

}
