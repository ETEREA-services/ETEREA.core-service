package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tipopax")
data class TipoPax(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "tipopax_id")
        val id: Long,
        @Column(name = "nombre") val nombre: String,
) : Auditable()
