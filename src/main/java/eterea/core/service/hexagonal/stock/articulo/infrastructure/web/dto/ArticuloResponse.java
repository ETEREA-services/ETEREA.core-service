package eterea.core.service.hexagonal.stock.articulo.infrastructure.web.dto;

import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.dto.CuentaResponse;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticuloResponse {

    private String articuloId;
    private Integer negocioId;
    private String descripcion;
    private String leyendaVoucher;
    private BigDecimal precioVentaSinIva;
    private BigDecimal precioVentaConIva;
    private Long numeroCuentaVentas;
    private Long numeroCuentaCompras;
    private Long numeroCuentaGastos;
    private Integer centroStockId;
    private Long rubroId;
    private Long subRubroId;
    private BigDecimal precioCompra;
    private byte iva105;
    private BigDecimal precioCompraNeto;
    private byte exento;
    private long stockMinimo;
    private long stockOptimo;
    private byte bloqueoCompras;
    private byte bloqueoStock;
    private byte bloqueoVentas;
    private Long unidadMedidaId;
    private byte conDecimales;
    private byte ventas;
    private byte compras;
    private String unidadMedida;
    private Integer conversionId;
    private byte ventaSinStock;
    private byte controlaStock;
    private byte asientoCostos;
    private String mascaraBalanza;
    private byte habilitaIngreso;
    private BigDecimal comision;
    private Integer prestadorId;
    private Long autoNumericoId;
    private CuentaResponse cuentaVentas;

}
