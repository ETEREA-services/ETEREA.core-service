package eterea.core.service.hexagonal.facturacion.arca.exportacion.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaExportacionResponse {

    @JsonProperty(value = "resultado")
    private String resultado;

    @JsonProperty(value = "cae")
    private String cae;

    @JsonProperty(value = "vencimiento_cae")
    private String vencimientoCae;

    @JsonProperty(value = "numero_comprobante")
    private Long numeroComprobante;

    @JsonProperty(value = "fecha_comprobante")
    private String fechaComprobante;

    @JsonProperty(value = "tipo_afip")
    private Integer tipoAfip;

    @JsonProperty(value = "punto_venta")
    private Integer puntoVenta;

    @JsonProperty(value = "cliente")
    private String cliente;

    @JsonProperty(value = "total")
    private BigDecimal total;

    @JsonProperty(value = "moneda_id")
    private String monedaId;

    @JsonProperty(value = "moneda_ctz")
    private BigDecimal cotizacion;

}
