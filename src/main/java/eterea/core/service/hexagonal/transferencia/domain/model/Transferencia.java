package eterea.core.service.hexagonal.transferencia.domain.model;

import eterea.core.service.tool.Jsonifier;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transferencia {

    private Long transferenciaId;
    private Integer negocioIdDesde;
    private Integer negocioIdHasta;
    private Long numeroTransferencia;
    private OffsetDateTime fecha;
    private BigDecimal total;
    private Integer ordenContable;
    private Byte transferido;
    private Integer comprobanteId;
    private OffsetDateTime fechaOtro;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }
}
