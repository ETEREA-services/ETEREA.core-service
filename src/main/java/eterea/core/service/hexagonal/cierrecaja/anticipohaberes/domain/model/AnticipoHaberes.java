package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnticipoHaberes {

    private Integer legajoId;
    private OffsetDateTime fecha;
    private BigDecimal importe;

}
