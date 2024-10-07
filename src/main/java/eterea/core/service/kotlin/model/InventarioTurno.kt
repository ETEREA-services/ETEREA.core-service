package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "inventarioturno")
data class InventarioTurno(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "int_id")
    var inventarioTurnoId: Int? = null,

    @Column(name = "int_nombre")
    var nombre: String = ""

) : Auditable() {

    class Builder {
        private var inventarioTurnoId: Int? = null
        private var nombre: String = ""

        fun inventarioTurnoId(inventarioTurnoId: Int?) = apply { this.inventarioTurnoId = inventarioTurnoId }
        fun nombre(nombre: String) = apply { this.nombre = nombre }

        fun build() = InventarioTurno(
            inventarioTurnoId = inventarioTurnoId,
            nombre = nombre
        )
    }
}
