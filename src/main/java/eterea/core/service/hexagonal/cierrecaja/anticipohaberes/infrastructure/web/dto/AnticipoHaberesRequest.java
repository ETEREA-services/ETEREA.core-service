package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnticipoHaberesRequest {

    @JsonProperty(value = "legajo_id")
    private Integer legajoId;

    @JsonProperty(value = "apellido_nombre")
    private String apellidoNombre;

    private OffsetDateTime fecha;
    private BigDecimal importe;

}
