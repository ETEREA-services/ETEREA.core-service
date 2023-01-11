/**
 * Entidad Articulo vinculada a la tabla articulos, a us vez esta tabla esta relacionada con
 * articulofecha, productoarticulo y articulomovimientoprevio
 *
 */
package eterea.api.rest.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "articulos")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Articulo extends Auditable implements Serializable {

	private static final long serialVersionUID = 3993609223567470857L;

	@Id
	@Column(name = "codigo")
	private String articuloId;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "clave")
	private Long uniqueId;

	@Column(name = "art_neg_id")
	private Integer negocioId;

	private String descripcion = "";

	@Column(name = "art_voucher")
	private String leyendaVoucher = "";

	@Column(name = "precioventasiniva")
	private BigDecimal precioVentaSinIva = BigDecimal.ZERO;

	@Column(name = "preciounitario")
	private BigDecimal precioVentaConIva = BigDecimal.ZERO;

	@Column(name = "cgocontable")
	private Long numeroCuentaVentas;

	@Column(name = "cgocontablecompras")
	private Long numeroCuentaCompras;

	@Column(name = "Cgocontablegastos")
	private Long numeroCuentaGastos;

	@Column(name = "cgocentro")
	private Integer centroStockId;

	@Column(name = "cgorubro")
	private Long rubroId;

	@Column(name = "cgosubrubro")
	private Long subRubroId;

	@Column(name = "preciocompra")
	private BigDecimal precioCompra = BigDecimal.ZERO;

	@Column(name = "iva105")
	private Byte iva105 = 0;

	@Column(name = "art_compraneto")
	private BigDecimal precioCompraNeto = BigDecimal.ZERO;

	private Byte exento = 0;

	@Column(name = "stockminimo")
	private Long stocMinimo = 0L;

	@Column(name = "stockoptimo")
	private Long stockOptimo = 0L;

	@Column(name = "bloqueocompras")
	private Byte bloqueoCompras = 0;

	@Column(name = "bloqueostock")
	private Byte bloqueoStock = 0;

	@Column(name = "bloqueoventas")
	private Byte bloqueoVentas = 0;

	@Column(name = "art_ume_id")
	private Long unidadMedidaId;

	@Column(name = "art_condecimales")
	private Byte conDecimales = 0;

	@Column(name = "art_ventas")
	private Byte ventas = 0;

	@Column(name = "art_compras")
	private Byte compras = 0;

	@Column(name = "umedida")
	private String unidadMedida = "";

	@Column(name = "art_con_id")
	private Integer conversionId;

	@Column(name = "art_ventasinstock")
	private Byte ventaSinStock = 0;

	@Column(name = "art_controlstock")
	private Byte controlaStock = 0;

	@Column(name = "art_asientocostos")
	private Byte asientoCostos = 0;

	@Column(name = "art_maskbal")
	private String mascaraBalanza = "";

	@Column(name = "art_habingreso")
	private Byte habilitaIngreso = 0;

	@Column(name = "art_comision")
	private BigDecimal comision = BigDecimal.ZERO;

	@Column(name = "art_pre_ID")
	private Integer prestadorId;

}
