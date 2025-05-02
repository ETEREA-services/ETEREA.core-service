package eterea.core.service.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ReservaHotelDto(
        Long clienteId,
        String telefono,
        String email,
        OffsetDateTime fechaIngreso,
        OffsetDateTime fechaSalida,
        Integer numeroHabitacion,
        Long tarifaId,
        BigDecimal precioUnitario,
        Integer cantidadPax,
        Integer paxMayor,
        Integer paxMenor,
        String observaciones,
        OffsetDateTime fechaOperacion,
        OffsetDateTime fechaVencimiento,
        Boolean tarifaStandard,
        String letraComprobante) {
}