package eterea.core.service.model.client.pyafipws;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FacturaResponseDto {

    @JsonProperty("mensaje")
    private String mensaje;

    @JsonProperty("factura")
    private FacturaDto factura;

    public String jsonify() {
        try {
            return JsonMapper
                    .builder()
                    .findAndAddModules()
                    .build()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "jsonify error {}" + e.getMessage();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FacturaDto {
        @JsonProperty("concepto")
        private Integer concepto;

        @JsonProperty("tipo_doc")
        private Integer tipoDoc;

        @JsonProperty("nro_doc")
        private Long nroDoc;

        @JsonProperty("tipo_cbte")
        private Integer tipoCbte;

        @JsonProperty("punto_vta")
        private Integer puntoVta;

        @JsonProperty("cbt_desde")
        private Long cbtDesde;

        @JsonProperty("cbt_hasta")
        private Long cbtHasta;

        @JsonProperty("fecha_cbte")
        @JsonFormat(pattern = "yyyyMMdd")
        private LocalDate fechaCbte;

        @JsonProperty("imp_total")
        private BigDecimal impTotal;

        @JsonProperty("imp_tot_conc")
        private BigDecimal impTotConc;

        @JsonProperty("imp_neto")
        private BigDecimal impNeto;

        @JsonProperty("imp_op_ex")
        private BigDecimal impOpEx;

        @JsonProperty("imp_trib")
        private BigDecimal impTrib;

        @JsonProperty("imp_iva")
        private BigDecimal impIva;

        @JsonProperty("fecha_serv_desde")
        private String fechaServDesde;

        @JsonProperty("fecha_serv_hasta")
        private String fechaServHasta;

        @JsonProperty("fecha_venc_pago")
        private String fechaVencPago;

        @JsonProperty("moneda_id")
        private String monedaId;

        @JsonProperty("moneda_ctz")
        private Integer monedaCtz;

        @JsonProperty("cbtes_asoc")
        private List<Object> cbtesAsoc;

        @JsonProperty("tributos")
        private List<Object> tributos;

        @JsonProperty("iva")
        private List<IvaDto> iva;

        @JsonProperty("opcionales")
        private List<Object> opcionales;

        @JsonProperty("compradores")
        private List<Object> compradores;

        @JsonProperty("actividades")
        private List<Object> actividades;

        @JsonProperty("cae")
        private String cae;

        @JsonProperty("resultado")
        private String resultado;

        @JsonProperty("fch_venc_cae")
        @JsonFormat(pattern = "yyyyMMdd")
        private LocalDate fchVencCae;

        @JsonProperty("obs")
        private List<Object> obs;

        public String jsonify() {
            try {
                return JsonMapper
                        .builder()
                        .findAndAddModules()
                        .build()
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(this);
            } catch (JsonProcessingException e) {
                return "jsonify error {}" + e.getMessage();
            }
        }

    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IvaDto {
        @JsonProperty("iva_id")
        private Integer ivaId;

        @JsonProperty("base_imp")
        private BigDecimal baseImp;

        @JsonProperty("importe")
        private BigDecimal importe;
    }

}
