package eterea.core.service.hexagonal.facturacion.arca.exportacion.infrastructure.web.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacturaExportacionContextRequest {

    private String ipAddress;
    private Long handler;
    private Integer comprobanteId;
    private Long clienteId;
    private Integer monedaId;
    private Integer tipoExportacion;
    private String permisoExistente;
    private Integer codigoPais;
    private Integer idioma;
    private String observacionesComerciales;
    private String observaciones;
    private String formaPago;
    private String incoTerms;
    private String incoTermsDS;
    private BigDecimal cotizacion;
    private BigDecimal total;

}
