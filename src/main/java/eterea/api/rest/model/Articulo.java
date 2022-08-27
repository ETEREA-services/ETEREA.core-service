/**
 * Entidad Articulo vinculada a la tabla articulos, a us vez esta tabla esta relacionada con
 * articulofecha, productoarticulo y articulomovimientoprevio
 *
 */
package eterea.api.rest.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articulos")
@AllArgsConstructor
public class Articulo extends Auditable implements Serializable {

	@Serial
	private static final long serialVersionUID = 3993609223567470857L;

	@Id
	@Column(name = "codigo")
	@Size(max = 20)
	@NotNull
	private String articuloId = "";

	@Column(name = "art_neg_id")
	@NotNull
	private Integer negocioId = 0;

	@Column(name = "descripcion")
	@Size(max = 50)
	@NotNull
	private String descripcion = "";

	@Column(name = "art_voucher")
	@Size(max = 150)
	@NotNull
	private String leyendaVoucher = "";

	@Column(name = "precioventasiniva")
	@NotNull
	private BigDecimal precioVentaSinIva = new BigDecimal(0);

	@Column(name = "preciounitario")
	@NotNull
	private BigDecimal precioVentaConIva = new BigDecimal(0);

	@Column(name = "cgocontable")
	private Long cuentaVentas;

	@Column(name = "cgocontablecompras")
	private Long cuentaCompras;

	@Column(name = "Cgocontablegastos")
	private Long cuentaGastos;

	@Column(name = "cgocentro")
	private Integer centroStockId;

	@Column(name = "cgorubro")
	private Long rubroId;

	@Column(name = "cgosubrubro")
	private Long subRubroId;

	@Column(name = "preciocompra")
	@NotNull
	private BigDecimal precioCompra = new BigDecimal(0);

	@Column(name = "iva105")
	@NotNull
	private Byte iva105 = 0;

	@Column(name = "art_compraneto")
	@NotNull
	private BigDecimal precioCompraNeto = new BigDecimal(0);

	@Column(name = "exento")
	@NotNull
	private Byte exento = 0;

	@Column(name = "stockminimo")
	@NotNull
	private Long stocMinimo = 0L;

	@Column(name = "stockoptimo")
	@NotNull
	private Long stockOptimo = 0L;

	@Column(name = "bloqueocompras")
	@NotNull
	private Byte bloqueoCompras = 0;

	@Column(name = "bloqueostock")
	@NotNull
	private Byte bloqueoStock = 0;

	@Column(name = "bloqueoventas")
	@NotNull
	private Byte bloqueoVentas = 0;

	@Column(name = "art_ume_id")
	private Long unidadMedidaId;

	@Column(name = "art_condecimales")
	@NotNull
	private Byte conDecimales = 0;

	@Column(name = "art_ventas")
	@NotNull
	private Byte ventas = 0;

	@Column(name = "art_compras")
	@NotNull
	private Byte compras = 0;

	@Column(name = "umedida")
	@Size(max = 20)
	@NotNull
	private String unidadMedida = "";

	@Column(name = "art_con_id")
	private Integer conversionId;

	@Column(name = "art_ventasinstock")
	@NotNull
	private Byte ventaSinStock = 0;

	@Column(name = "art_controlstock")
	@NotNull
	private Byte controlaStock = 0;

	@Column(name = "art_asientocostos")
	@NotNull
	private Byte asientoCostos = 0;

	@Column(name = "art_maskbal")
	@Size(max = 5)
	@NotNull
	private String mascaraBalanza = "";

	@Column(name = "art_habingreso")
	@NotNull
	private Byte habilitaIngreso = 0;

	@Column(name = "art_comision")
	@NotNull
	private BigDecimal comision = new BigDecimal(0);

	@Column(name = "art_pre_ID")
	private Integer prestadorId;

	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long autoNumericoId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Articulo articulo = (Articulo) o;
		return articuloId != null && Objects.equals(articuloId, articulo.articuloId);
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}
}
