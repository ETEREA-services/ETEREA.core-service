package eterea.core.service.hexagonal.proveedormovimiento.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumenIvaComprasMensualPosicion {

    private Integer negocioId;
    private Integer anho;
    private Integer mes;
    private Integer posicion;
    private BigDecimal neto;
    private BigDecimal facturadoC;
    private BigDecimal gastosNoGravados;
    private BigDecimal iva21;
    private BigDecimal iva27;
    private BigDecimal iva105;
    private BigDecimal percepcionIva;
    private BigDecimal percepcionIngresosBrutos;
    private BigDecimal total;

}
