package eterea.core.service.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import eterea.core.service.validation.ValidDateRange;

@ValidDateRange
public record CreateHabitacionMovimientoDto(
        @NotNull
        Integer tipoDocumentoId,
        String cuit,
        String nroDocumento,
        @NotNull
        OffsetDateTime fechaIngreso,
        @NotNull
        OffsetDateTime fechaSalida,
        @NotNull
        Integer numeroHabitacion,
        Long tarifaId,
        BigDecimal precioUnitario,
        @NotNull
        @Min(1)
        Integer cantidadPax,
        Integer paxMayor,
        Integer paxMenor,
        String observaciones,
        @NotNull
        OffsetDateTime fechaOperacion,
        @NotNull
        OffsetDateTime fechaVencimiento,
        Boolean tarifaStandard,
        @NotNull
        @Pattern(regexp = "[A-Z]")
        @Size(min = 1, max = 1)
        String letraComprobante) {
}