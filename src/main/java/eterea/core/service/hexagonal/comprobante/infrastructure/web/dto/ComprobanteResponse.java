package eterea.core.service.hexagonal.comprobante.infrastructure.web.dto;

import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.dto.CuentaResponse;
import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ComprobanteAfipInvoiceDataResponse;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteResponse {

    private Integer comprobanteId;
    private String descripcion;
    private Integer negocioId;
    private Integer modulo;
    private Byte stock;
    private Byte rendicion;
    private Byte ordenPago;
    private Byte aplicaPendiente;
    private Byte cuentaCorriente;
    private Byte debita;
    private Byte iva;
    private Byte ganancias;
    private Byte aplicable;
    private Byte libroIva;
    private String letraComprobante;
    private Byte recibo;
    private Byte contado;
    private Byte transferencia;
    private Byte imprime;
    private Integer comprobanteIdAnulacion;
    private Integer centroStockIdEntrega;
    private Integer centroStockIdRecibe;
    private Integer diasVigencia;
    private Byte concepto;
    private Byte personal;
    private Byte comanda;
    private Integer puntoVenta;
    private Byte codigoMozo;
    private Byte transferir;
    private Long numeroCuenta;
    private Integer nivel;
    private Byte fiscalizadora;
    private Byte invisible;
    private Integer comprobanteAfipId;
    private Byte facturaElectronica;
    private Byte asociado;

    private CuentaResponse cuenta;
    private ComprobanteAfipInvoiceDataResponse comprobanteAfip;

}
