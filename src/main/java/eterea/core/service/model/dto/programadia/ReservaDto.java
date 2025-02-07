package eterea.core.service.model.dto.programadia;

import java.time.OffsetDateTime;

public record ReservaDto(
      Long reservaId,
      String nombrePax,
      Integer cantidadPaxs,
      String observaciones,
      Boolean facturada,
      OffsetDateTime fechaToma,
      OffsetDateTime fechaInServicio,
      OffsetDateTime fechaOutServicio,
      OffsetDateTime fechaVencimiento) {

}
