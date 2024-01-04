package eterea.core.api.rest.kotlin.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(
    name = "registrocae",
    uniqueConstraints = [UniqueConstraint(columnNames = ["rec_tco_id", "rec_prefijo", "rec_nrocomprob"])]
)
data class Electronico(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rec_id")
    var electronicoId: Long? = null,

    @Column(name = "rec_tco_id")
    var comprobanteId: Int? = null,

    @Column(name = "rec_prefijo")
    var puntoVenta: Int? = 0,

    @Column(name = "rec_nrocomprob")
    var numeroComprobante: Long? = 0L,

    @Column(name = "rec_cli_id")
    var clienteId: Long? = null,

    @Column(name = "rec_cuit")
    var cuit: String? = null,

    @Column(name = "rec_total")
    var total: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_exento")
    var exento: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_neto")
    var neto21: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_neto105")
    var neto105: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_iva")
    var iva21: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_iva105")
    var iva105: BigDecimal = BigDecimal.ZERO,

    @Column(name = "rec_cae")
    var cae: String = "",

    @Column(name = "rec_fecha")
    var fecha: String = "",

    @Column(name = "rec_caevenc")
    var caeVencimiento: String = "",

    @Column(name = "rec_barras")
    var codigoBarras: String = "",

    @Column(name = "tipo_documento")
    var tipoDocumento: Int? = null,

    @Column(name = "numero_documento")
    var numeroDocumento: BigDecimal? = null,

    @Column(name = "cliente_movimiento_id_asociado")
    var clienteMovimientoIdAsociado: Long? = null

) : Auditable()
