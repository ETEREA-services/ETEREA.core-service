package eterea.core.service.model.dto.reserva;

import java.math.BigDecimal;

public record ReservaArticuloRequestDto(

      String articuloId,
      Integer cantidad,
      BigDecimal comision,
      Long reservaId) {

}
