package eterea.core.service.hexagonal.transferencia.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaResponse {

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

}
