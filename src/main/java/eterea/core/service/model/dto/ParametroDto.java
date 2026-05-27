package eterea.core.service.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import eterea.core.service.tool.Jsonifier;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ParametroDto {

    private Long parametroId;
    private Integer negocioId;
    private Long cuentaProveedor;
    private Long cuentaIva21Compras;
    private Long cuentaIva27Compras;
    private Long cuentaIva205Compras;
    private Long cuentaPercepcionIvaCompra;
    private Long cuentaPercepcionIngresosBrutosCompra;
    private Long cuentaGastosNoGravadosCompra;
    private Long cuentaClientes;
    private Long cuentaIvaVentas;
    private Long cuentaIvaRniVentas;
    private Long cuentaVentas;
    private Long cuentaCaja;
    private Long cuentaAjuste;
    private Long cuentaStockConfirmar;

    @Builder.Default
    private BigDecimal iva1 = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal iva2 = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal ivaCredito1 = BigDecimal.ZERO;
    private Byte bloqueIvaCompras;

    @Builder.Default
    private BigDecimal ivaVenta = BigDecimal.ZERO;
    private Integer centroStockIdIngreso;
    private Integer centroStockIdRestaurant;
    private Byte facturaElectronicaProduccion;

    @Builder.Default
    private BigDecimal porcentajeDescuentoPersonal = BigDecimal.ZERO;

    public String jsonify() {
        return Jsonifier.builder(this).build();
    }

}
