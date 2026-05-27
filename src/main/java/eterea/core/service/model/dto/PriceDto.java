package eterea.core.service.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceDto {

    private String articuloId;
    private OffsetDateTime fechaInicio;
    private OffsetDateTime fechaFin;
    @Builder.Default
    private BigDecimal precioLunes = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioMartes = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioMiercoles = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioJueves = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioViernes = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioSabado = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioDomingo = BigDecimal.ZERO;
    @Builder.Default
    private BigDecimal precioFeriado = BigDecimal.ZERO;

}
