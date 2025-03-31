package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tipodia")
data class TipoDia(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipodia_id")
    val id: Long,
    @Column(name = "nombre")
    val nombre: String,
) : Auditable()
