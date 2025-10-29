package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "centrosstock")
data class CentroStock(

    @Id
    @Column(name = "codigo")
    val centroStockId: Int? = null,

    val descripcion: String = "",

    @Column(name = "cst_neg_id")
    val negocioId: Int? = null,

    @Column(name = "cst_contable")
    val numeroCuenta: Long? = null,

    @Column(name = "cst_controlastock")
    val controlaStock: Byte = 0,

    @Column(name = "cst_tipo")
    val tipo: Int = 0,

) : Auditable() {

    class Builder {
        private var centroStockId: Int? = null
        private var descripcion: String = ""
        private var negocioId: Int? = null
        private var numeroCuenta: Long? = null
        private var controlaStock: Byte = 0
        private var tipo: Int = 0

        fun centroStockId(centroStockId: Int?) = apply { this.centroStockId = centroStockId }
        fun descripcion(descripcion: String) = apply { this.descripcion = descripcion }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun numeroCuenta(numeroCuenta: Long?) = apply { this.numeroCuenta = numeroCuenta }
        fun controlaStock(controlaStock: Byte) = apply { this.controlaStock = controlaStock }
        fun tipo(tipo: Int) = apply { this.tipo = tipo }

        fun build() = CentroStock(
            centroStockId = centroStockId,
            descripcion = descripcion,
            negocioId = negocioId,
            numeroCuenta = numeroCuenta,
            controlaStock = controlaStock,
            tipo = tipo
        )
    }
}