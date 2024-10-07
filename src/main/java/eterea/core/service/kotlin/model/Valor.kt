package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "cgosvalores")
data class Valor(

    @Id @Column(name = "codigo")
    var valorId: Int? = null,

    var concepto: String? = null,

    @Column(name = "cva_neg_id")
    var negocioId: Int? = null,

    @Column(name = "modulocompras")
    var compras: Byte = 0,

    @Column(name = "moduloventas")
    var ventas: Byte = 0,

    var numerable: Byte = 0,
    var duplicados: Byte = 0,

    @Column(name = "fechaemi")
    var fechaEmision: Byte = 0,

    @Column(name = "fechavto")
    var fechaVencimiento: Byte = 0,

    @Column(name = "cgocontable")
    var numeroCuenta: Long? = null,

    var titular: Byte = 0,
    var banco: Byte = 0,

    @Column(name = "chtercero")
    var chequeTercero: Byte = 0,

    @Column(name = "ctacte")
    var cuentaCorriente: Byte = 0,

    @Column(name = "retib")
    var retencionIngresosBrutos: Byte = 0,

    @Column(name = "cva_retgcias")
    var retencionGanancias: Byte = 0,

    @Column(name = "cva_retiva")
    var retencionIva: Byte = 0,

    @Column(name = "cva_autonumerable")
    var autoNumerable: Byte = 0,

    @Column(name = "cva_mon_id")
    var monedaId: Int? = null,

    @Column(name = "cva_invisible")
    var invisible: Byte = 0,

    var restaurant: Byte = 0,
    var tarjeta: Byte = 0

) : Auditable()
