package eterea.api.rest.model

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.Column
import java.math.BigDecimal
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType

@Entity
@Table(name = "articulos")
data class Articulo(

	@Id
	@Column(name = "codigo")
	var articuloId: String = "",

	@Column(name = "art_neg_id")
	var negocioId: Int = 0,

	var descripcion: String = "",

	@Column(name = "art_voucher")
	var leyendaVoucher: String = "",

	@Column(name = "precioventasiniva")
	var precioVentaSinIva: BigDecimal = BigDecimal.ZERO,

	@Column(name = "preciounitario")
	var precioVentaConIva: BigDecimal = BigDecimal.ZERO,

	@Column(name = "cgocontable")
	var cuentaVentas: Long? = null,

	@Column(name = "cgocontablecompras")
	var cuentaCompras: Long? = null,

	@Column(name = "Cgocontablegastos")
	var cuentaGastos: Long? = null,

	@Column(name = "cgocentro")
	var centroStockId: Int? = null,

	@Column(name = "cgorubro")
	var rubroId: Long? = null,

	@Column(name = "cgosubrubro")
	var subRubroId: Long? = null,

	@Column(name = "preciocompra")
	var precioCompra: BigDecimal = BigDecimal.ZERO,

	@Column(name = "iva105")
	var iva105: Byte = 0,

	@Column(name = "art_compraneto")
	var precioCompraNeto: BigDecimal = BigDecimal.ZERO,

	@Column(name = "exento")
	var exento: Byte = 0,

	@Column(name = "stockminimo")
	var stocMinimo: Long = 0L,

	@Column(name = "stockoptimo")
	var stockOptimo: Long = 0L,

	@Column(name = "bloqueocompras")
	var bloqueoCompras: Byte = 0,

	@Column(name = "bloqueostock")
	var bloqueoStock: Byte = 0,

	@Column(name = "bloqueoventas")
	var bloqueoVentas: Byte = 0,

	@Column(name = "art_ume_id")
	var unidadMedidaId: Long? = null,

	@Column(name = "art_condecimales")
	var conDecimales: Byte = 0,

	@Column(name = "art_ventas")
	var ventas: Byte = 0,

	@Column(name = "art_compras")
	var compras: Byte = 0,

	@Column(name = "umedida")
	var unidadMedida: String = "",

	@Column(name = "art_con_id")
	var conversionId: Int? = null,

	@Column(name = "art_ventasinstock")
	var ventaSinStock: Byte = 0,

	@Column(name = "art_controlstock")
	var controlaStock: Byte = 0,

	@Column(name = "art_asientocostos")
	var asientoCostos: Byte = 0,

	@Column(name = "art_maskbal")
	var mascaraBalanza: String = "",

	@Column(name = "art_habingreso")
	var habilitaIngreso: Byte = 0,

	@Column(name = "art_comision")
	var comision: BigDecimal = BigDecimal.ZERO,

	@Column(name = "art_pre_ID")
	var prestadorId: Int? = null,

	@Column(name = "clave")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var autoNumericoId: Long? = null

) : Auditable()

