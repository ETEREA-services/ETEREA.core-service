package eterea.core.service.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ValorMovimientoDto(Long valorMovimientoId, Integer negocioId, String concepto,
            OffsetDateTime fechaContable, BigDecimal importe) {

}
