package eterea.core.service.model.dto.reserva;

import java.util.List;

import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ReservaArticulo;

public record CreateReservaDto(
      Reserva reserva,
      List<ReservaArticulo> reservaArticulos,
      String clienteNumeroDocumento
) {
}
