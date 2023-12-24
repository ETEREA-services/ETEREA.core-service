package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "plancta")
data class Cuenta(

    @Id @Column(name = "cuenta")
    var numeroCuenta: Long? = null,

    var nombre: String? = null,

    @Column(name = "pla_neg_id")
    var negocioId: Int? = null,

    var integra: Byte? = null,

    @Column(name = "pla_transfiere")
    var transfiere: Byte? = null,

    @Column(name = "pla_movstock")
    var movimientoStock: Byte? = null,

    @Column(name = "pla_cuentamaestro")
    var cuentaMaestro: BigDecimal = BigDecimal.ZERO,

    @Column(name = "pla_grado")
    var grado: Int? = null,

    @Column(name = "pla_grado1")
    var grado1: Long? = null,

    @Column(name = "pla_grado2")
    var grado2: Long? = null,

    @Column(name = "pla_grado3")
    var grado3: Long? = null,

    @Column(name = "pla_grado4")
    var grado4: Long? = null,

    @Column(name = "pla_ventas")
    var ventas: Byte? = null,

    @Column(name = "pla_compras")
    var compras: Byte? = null,

    @Column(name = "pla_gastos")
    var gastos: Byte? = null

) : Auditable()
