package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnticipoHaberesResponse {

    @JsonProperty(value = "legajo_id")
    private Integer legajoId;
    private BigDecimal importe;

}
