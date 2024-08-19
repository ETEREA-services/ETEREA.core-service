package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "usuarios")
data class Usuario(

    @Id
    @Column(name = "nombre")
    var login: String? = null,

    @Column(name = "Usu_Descripcion")
    var descripcion: String? = null,

    @Column(name = "Usu_Password")
    var password: String? = null,

    @Column(name = "Usu_Correo")
    var email: String? = null,

    @Column(name = "Usu_Nivel")
    var nivel: Int = 0,

    @Column(name = "Usu_PIN")
    var pin: String? = null,

    @Column(name = "Usu_CierRecType")
    var cierreRecipientType: String? = null,

    @Column(name = "Usu_ConsRecType")
    var consolidadoRecipientType: String? = null,

    @Column(name = "clave")
    var usuarioId: Long? = null

) : Auditable() {

    data class Builder(
        private var login: String? = null,
        private var descripcion: String? = null,
        private var password: String? = null,
        private var email: String? = null,
        private var nivel: Int = 0,
        private var pin: String? = null,
        private var cierreRecipientType: String? = null,
        private var consolidadoRecipientType: String? = null,
        private var usuarioId: Long? = null
    ) {
        fun login(login: String) = apply { this.login = login }
        fun descripcion(descripcion: String) = apply { this.descripcion = descripcion }
        fun password(password: String) = apply { this.password = password }
        fun email(email: String) = apply { this.email = email }
        fun nivel(nivel: Int) = apply { this.nivel = nivel }
        fun pin(pin: String) = apply { this.pin = pin }
        fun cierreRecipientType(cierreRecipientType: String) = apply { this.cierreRecipientType = cierreRecipientType }
        fun consolidadoRecipientType(consolidadoRecipientType: String) = apply { this.consolidadoRecipientType = consolidadoRecipientType }
        fun usuarioId(usuarioId: Long) = apply { this.usuarioId = usuarioId }

        fun build() = Usuario(
            login,
            descripcion,
            password,
            email,
            nivel,
            pin,
            cierreRecipientType,
            consolidadoRecipientType,
            usuarioId
        )
    }
}
