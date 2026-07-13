package eterea.core.service.hexagonal.comprobante.domain.model;

import eterea.core.service.hexagonal.contable.cuenta.domain.model.Cuenta;
import eterea.core.service.kotlin.model.ComprobanteAfip;
import eterea.core.service.tool.Jsonifyable;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comprobante implements Jsonifyable {

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

    private Cuenta cuenta;
    private ComprobanteAfip comprobanteAfip;

}
