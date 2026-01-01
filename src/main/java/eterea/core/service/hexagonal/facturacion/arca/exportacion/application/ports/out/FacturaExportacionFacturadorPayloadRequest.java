package eterea.core.service.hexagonal.facturacion.arca.exportacion.application.ports.out;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturaExportacionFacturadorPayloadRequest {

    @JsonProperty(value = "tipo_afip")
    private Integer tipoAfip;

    @JsonProperty(value = "punto_venta")
    private Integer puntoVenta;

    @JsonProperty(value = "cliente")
    private String cliente;

    @JsonProperty(value = "domicilio_cliente")
    private String domicilioCliente;

    @JsonProperty(value = "pais_dst_cmp")
    private Integer codigoPais;

    @JsonProperty(value = "id_impositivo")
    private String idImpositivo;

    @JsonProperty(value = "total")
    private BigDecimal total;

    @JsonProperty(value = "moneda_id")
    private String monedaId;

    @JsonProperty(value = "moneda_ctz")
    private BigDecimal cotizacion;

    @JsonProperty(value = "incoterms")
    private String incoTerms;

    @JsonProperty(value = "tipo_expo")
    private Integer tipoExportacion;

    @JsonProperty(value = "permiso_existente")
    private String permisoExistente;

    @JsonProperty(value = "items")
    private List<FacturaExportacionFacturadorItem> items;

}
