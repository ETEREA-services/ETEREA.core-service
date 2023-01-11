/**
 * 
 */
package eterea.api.rest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Immutable;

import eterea.api.rest.model.Auditable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author daniel
 *
 */
@Data
@Entity
@Immutable
@Table(name = "vw_articulo_search")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ArticuloSearch extends Auditable implements Serializable {

	private static final long serialVersionUID = 9101274129056442906L;

	@Id
	private String articuloId;

	private Long uniqueId;
	private Integer negocioId;
	private String descripcion = "";
	private String leyendaVoucher = "";
	private BigDecimal precioVentaSinIva = BigDecimal.ZERO;
	private BigDecimal precioVentaConIva = BigDecimal.ZERO;
	private Long numeroCuentaVentas;
	private Long numeroCuentaCompras;
	private Long numeroCuentaGastos;
	private Integer centroStockId;
	private Long rubroId;
	private Long subRubroId;
	private BigDecimal precioCompra = BigDecimal.ZERO;
	private Byte iva105 = 0;
	private BigDecimal precioCompraNeto = BigDecimal.ZERO;
	private Byte exento = 0;
	private Long stockMinimo = 0L;
	private Long stockOptimo = 0L;
	private Byte bloqueoCompras = 0;
	private Byte bloqueoStock = 0;
	private Byte bloqueoVentas = 0;
	private Long unidadMedidaId;
	private Byte conDecimales = 0;
	private Byte ventas = 0;
	private Byte compras = 0;
	private String unidadMedida = "";
	private Integer conversionId;
	private Byte ventaSinStock = 0;
	private Byte controlaStock = 0;
	private Byte asientoCostos = 0;
	private String mascaraBalanza = "";
	private Byte habilitaIngreso = 0;
	private BigDecimal comision = BigDecimal.ZERO;
	private Integer prestadorId;
	private String search;

}
