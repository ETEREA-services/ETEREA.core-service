package eterea.core.service.hexagonal.invoicedata.infrastructure.dto;

import eterea.core.service.tool.Jsonifyable;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class ClienteMovimientoInvoiceDataResponse implements Jsonifyable {

    private String letraComprobante;
    private Integer puntoVenta;
    private Long numeroComprobante;
    private String observaciones;
    private String letras;
    private Long reservaId;
    private BigDecimal neto;
    private BigDecimal montoExento;
    private BigDecimal montoIva;
    private BigDecimal montoIvaRni;
    private BigDecimal importe;
    private EmpresaInvoiceDataResponse empresa;
    private ClienteInvoiceDataResponse cliente;
    private MonedaInvoiceDataResponse moneda;
    private ComprobanteInvoiceDataResponse comprobante;
    private List<ArticuloMovimientoInvoiceDataResponse> articulos;

}
