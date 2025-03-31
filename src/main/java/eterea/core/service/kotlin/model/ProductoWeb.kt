package eterea.core.service.kotlin.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.Column

@Entity
@Table(name = "producto_web")
data class ProductoWeb(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "producto_web_id")
    val id: Long,
    @Column(name = "producto_nombre")
    val nombre: String,
    @Column(name = "producto_sku")
    val sku: String,
) : Auditable()