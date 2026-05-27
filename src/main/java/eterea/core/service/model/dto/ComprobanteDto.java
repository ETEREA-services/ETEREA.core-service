package eterea.core.service.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ComprobanteDto {

    private Integer comprobanteId;

    @Builder.Default
    private String descripcion = "";
    private Integer negocioId;
    private Integer modulo;

    @Builder.Default
    private Byte stock = 0;
    @Builder.Default
    private Byte rendicion = 0;
    @Builder.Default
    private Byte ordenPago = 0;
    @Builder.Default
    private Byte aplicaPendiente = 0;
    @Builder.Default
    private Byte cuentaCorriente = 0;
    @Builder.Default
    private Byte debita = 0;
    @Builder.Default
    private Byte iva = 0;
    @Builder.Default
    private Byte ganancias = 0;
    @Builder.Default
    private Byte aplicable = 0;
    @Builder.Default
    private Byte libroIva = 0;
    private String letraComprobante;
    @Builder.Default
    private Byte recibo = 0;
    @Builder.Default
    private Byte contado = 0;
    @Builder.Default
    private Byte transferencia = 0;
    @Builder.Default
    private Byte imprime = 0;
    private Integer comprobanteIdAnulacion;
    private Integer centroStockIdEntrega;
    private Integer centroStockIdRecibe;
    private Integer diasVigencia;
    @Builder.Default
    private Byte concepto = 0;
    @Builder.Default
    private Byte personal = 0;
    @Builder.Default
    private Byte comanda = 0;
    @Builder.Default
    private Integer puntoVenta = 0;
    @Builder.Default
    private Byte codigoMozo = 0;
    @Builder.Default
    private Byte transferir = 0;
    private Long numeroCuenta;
    @Builder.Default
    private Integer nivel = 0;
    @Builder.Default
    private Byte fiscalizadora = 0;
    @Builder.Default
    private Byte invisible = 0;
    private Integer comprobanteAfipId;
    @Builder.Default
    private Byte facturaElectronica = 0;
    @Builder.Default
    private Byte asociado = 0;
    private CuentaDto cuenta;

}
