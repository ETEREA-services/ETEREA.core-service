package eterea.core.service.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "reservacomentario")
data class ReservaComentario(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clave")
    var id: Long? = null,
    @Column(name = "rec_usuario")
    var usuario: String? = null,
    @Column(name = "rec_comentario")
    var comentario: String? = null,
    @ManyToOne
    @JoinColumn(name = "rec_res_id")
    var reserva: Reserva? = null
) : Auditable() {

    class Builder {
        var id: Long? = null
        var usuario: String? = null
        var comentario: String? = null
        var reserva: Reserva? = null

        fun id(id: Long?) = apply { this.id = id }
        fun usuario(usuario: String?) = apply { this.usuario = usuario }
        fun comentario(comentario: String?) = apply { this.comentario = comentario }
        fun reserva(reserva: Reserva?) = apply { this.reserva = reserva }
        fun build() = ReservaComentario(id, usuario, comentario, reserva)
    }
}