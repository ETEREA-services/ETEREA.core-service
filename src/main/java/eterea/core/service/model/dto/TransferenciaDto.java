package eterea.core.service.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import eterea.core.service.tool.Jsonifier;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDto {

    private Long transferenciaId;
    private Integer negocioIdDesde;
    private Integer negocioIdHasta;
    private Long numeroTransferencia;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fecha;

    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;
    private Integer ordenContable;

    @Builder.Default
    private Byte transferido = 0;
    private Integer comprobanteId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXX", timezone = "UTC")
    private OffsetDateTime fechaOtro;

    private ComprobanteDto comprobante;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }
}
