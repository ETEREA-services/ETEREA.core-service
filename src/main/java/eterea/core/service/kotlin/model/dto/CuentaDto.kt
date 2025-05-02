package eterea.core.service.kotlin.model.dto

import eterea.core.service.kotlin.model.Cuenta
import java.math.BigDecimal

data class CuentaDto(
    var numeroCuenta: Long? = null,
    var nombre: String? = null,
    var negocioId: Int? = null,
    var integra: Byte? = null,
    var transfiere: Byte? = null,
    var movimientoStock: Byte? = null,
    var cuentaMaestro: BigDecimal = BigDecimal.ZERO,
    var grado: Int? = null,
    var grado1: Long? = null,
    var grado2: Long? = null,
    var grado3: Long? = null,
    var grado4: Long? = null,
    var ventas: Byte? = null,
    var compras: Byte? = null,
    var gastos: Byte? = null
) {
    class Builder {
        private var numeroCuenta: Long? = null
        private var nombre: String? = null
        private var negocioId: Int? = null
        private var integra: Byte? = null
        private var transfiere: Byte? = null
        private var movimientoStock: Byte? = null
        private var cuentaMaestro: BigDecimal = BigDecimal.ZERO
        private var grado: Int? = null
        private var grado1: Long? = null
        private var grado2: Long? = null
        private var grado3: Long? = null
        private var grado4: Long? = null
        private var ventas: Byte? = null
        private var compras: Byte? = null
        private var gastos: Byte? = null

        fun numeroCuenta(numeroCuenta: Long?) = apply { this.numeroCuenta = numeroCuenta }
        fun nombre(nombre: String?) = apply { this.nombre = nombre }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun integra(integra: Byte?) = apply { this.integra = integra }
        fun transfiere(transfiere: Byte?) = apply { this.transfiere = transfiere }
        fun movimientoStock(movimientoStock: Byte?) = apply { this.movimientoStock = movimientoStock }
        fun cuentaMaestro(cuentaMaestro: BigDecimal) = apply { this.cuentaMaestro = cuentaMaestro }
        fun grado(grado: Int?) = apply { this.grado = grado }
        fun grado1(grado1: Long?) = apply { this.grado1 = grado1 }
        fun grado2(grado2: Long?) = apply { this.grado2 = grado2 }
        fun grado3(grado3: Long?) = apply { this.grado3 = grado3 }
        fun grado4(grado4: Long?) = apply { this.grado4 = grado4 }
        fun ventas(ventas: Byte?) = apply { this.ventas = ventas }
        fun compras(compras: Byte?) = apply { this.compras = compras }
        fun gastos(gastos: Byte?) = apply { this.gastos = gastos }

        fun build() = CuentaDto(
            numeroCuenta,
            nombre,
            negocioId,
            integra,
            transfiere,
            movimientoStock,
            cuentaMaestro,
            grado,
            grado1,
            grado2,
            grado3,
            grado4,
            ventas,
            compras,
            gastos
        )
    }

    companion object {
        fun builder() = Builder()

        fun fromEntity(cuenta: Cuenta) = CuentaDto(
            numeroCuenta = cuenta.numeroCuenta,
            nombre = cuenta.nombre,
            negocioId = cuenta.negocioId,
            integra = cuenta.integra,
            transfiere = cuenta.transfiere,
            movimientoStock = cuenta.movimientoStock,
            cuentaMaestro = cuenta.cuentaMaestro,
            grado = cuenta.grado,
            grado1 = cuenta.grado1,
            grado2 = cuenta.grado2,
            grado3 = cuenta.grado3,
            grado4 = cuenta.grado4,
            ventas = cuenta.ventas,
            compras = cuenta.compras,
            gastos = cuenta.gastos
        )
    }

    fun toEntity() = Cuenta(
        numeroCuenta = this.numeroCuenta,
        nombre = this.nombre,
        negocioId = this.negocioId,
        integra = this.integra,
        transfiere = this.transfiere,
        movimientoStock = this.movimientoStock,
        cuentaMaestro = this.cuentaMaestro,
        grado = this.grado,
        grado1 = this.grado1,
        grado2 = this.grado2,
        grado3 = this.grado3,
        grado4 = this.grado4,
        ventas = this.ventas,
        compras = this.compras,
        gastos = this.gastos
    )
}
