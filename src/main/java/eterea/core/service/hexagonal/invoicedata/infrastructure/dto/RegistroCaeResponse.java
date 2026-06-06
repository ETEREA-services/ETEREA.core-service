package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import eterea.core.service.tool.Jsonifier;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class RegistroCaeResponse {

    private Integer tipoDocumento;
    private Integer puntoVenta;
    private Integer comprobanteId;
    private Long numeroComprobante;
    private BigDecimal total;
    private BigDecimal numeroDocumento;
    private String cae;
    private String caeVencimiento;
    private String fecha;
    private ComprobanteResponse comprobante;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
