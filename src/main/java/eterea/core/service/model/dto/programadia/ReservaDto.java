package eterea.core.service.model.dto.programadia;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.service.kotlin.model.ReservaComentario;

public record ReservaDto(
      Long reservaId,
      String nombrePax,
      Integer cantidadPaxs,
      String observaciones,
      Boolean facturada,
      String email,
      List<ReservaComentario> comentarios,
      OffsetDateTime fechaToma,
      OffsetDateTime fechaInServicio,
      OffsetDateTime fechaOutServicio,
      OffsetDateTime fechaVencimiento,
      LocalDateTime updated) {

}
