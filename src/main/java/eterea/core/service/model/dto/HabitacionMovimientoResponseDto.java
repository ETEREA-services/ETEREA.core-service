package eterea.core.service.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record HabitacionMovimientoResponseDto(
      Long habitacionMovimientoId,
      Long numeroReserva,
      OffsetDateTime fechaIngreso,
      OffsetDateTime fechaSalida,
      Long tarifaId,
      String conceptoTarifa,
      BigDecimal precioUnitarioTarifa,
      Long cantidadPax,
      Long diasFacturados,
      BigDecimal importeFacturado,
      OffsetDateTime fechaOperacion,
      OffsetDateTime fechaVencimiento,
      Long item,
      Byte tarifaStandard,
      Integer cantidadPaxMayor,
      Integer cantidadPaxMenor,
      String observaciones,
      Long clienteId,
      String letraComprobante,
      Integer numeroHabitacion) {
}
