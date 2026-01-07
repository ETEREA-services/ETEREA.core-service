package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumenIvaComprasMensualResponse {

    private Integer negocioId;
    private String negocio;
    private Integer anho;
    private Integer mes;
    private BigDecimal neto;
    private BigDecimal facturadoC;
    private BigDecimal gastosNoGravados;
    private BigDecimal iva21;
    private BigDecimal iva27;
    private BigDecimal iva105;
    private BigDecimal percepcionIva;
    private BigDecimal percepcionIngresosBrutos;
    private BigDecimal totalCalculado;
    private BigDecimal total;

}
