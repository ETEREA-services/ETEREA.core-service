package eterea.core.service.hexagonal.facturacion.arca.exportacion.application.ports.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FacturaExportacionFacturadorItem {

    @JsonProperty(value = "pro_codigo")
    private String articuloId;

    @JsonProperty(value = "pro_ds")
    private String descripcion;

    @JsonProperty(value = "pro_qty")
    private Integer cantidad;

    @JsonProperty(value = "pro_umed")
    private Integer unidadMedida;

    @JsonProperty(value = "pro_precio_uni")
    private BigDecimal precioUnitario;

    @JsonProperty(value = "pro_total_item")
    private BigDecimal totalItem;

}
