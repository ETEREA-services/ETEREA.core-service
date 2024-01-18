package eterea.core.api.rest.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "clientes")
data class Cliente(

    @Id
    @Column(name = "codigo")
    var clienteId: Long? = null,

    var nombre: String? = null,

    @Column(name = "cli_neg_id")
    var negocioId: Int? = null,

    var cuit: String = "",

    @Column(name = "razon")
    var razonSocial: String = "",

    @Column(name = "cli_fantasia")
    var nombreFantasia: String = "",

    @Column(name = "cli_fecharestaurant")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaRestaurant: OffsetDateTime? = null,

    @Column(name = "cli_cantpaxs")
    var cantidadPaxs: Int = 0,

    @Column(name = "cli_tipo")
    var tipoCliente: Int = 0,

    var domicilio: String = "",

    @Column(name = "tel")
    var telefono: String = "",

    var fax: String = "",
    var email: String = "",

    @Column(name = "celular")
    var numeroMovil: String = "",

    @Column(name = "posicion")
    var posicionIva: Int = 0,

    var constante: Int = 0,
    var documentoId: Int = 0,

    @Column(name = "tipodoc")
    var tipoDocumento: String = "",

    @Column(name = "nrodoc")
    var numeroDocumento: String = "",

    @Column(name = "limitecredito")
    var limiteCredito: BigDecimal = BigDecimal.ZERO,

    var nacionalidad: String = "",

    @Column(name = "cli_cca_id")
    var clienteCategoriaId: Int? = null,

    @Column(name = "cli_idimpositivo")
    var impositivoId: String = "",

    @Column(name = "facturarextranjero")
    var facturarExtranjero: Byte = 0,

    var bloqueado: Byte = 0,
    var discapacitado: Byte = 0

) : Auditable() {
    data class Builder(
        var clienteId: Long? = null,
        var nombre: String? = null,
        var negocioId: Int? = null,
        var cuit: String = "",
        var razonSocial: String = "",
        var nombreFantasia: String = "",
        var fechaRestaurant: OffsetDateTime? = null,
        var cantidadPaxs: Int = 0,
        var tipoCliente: Int = 0,
        var domicilio: String = "",
        var telefono: String = "",
        var fax: String = "",
        var email: String = "",
        var numeroMovil: String = "",
        var posicionIva: Int = 0,
        var constante: Int = 0,
        var documentoId: Int = 0,
        var tipoDocumento: String = "",
        var numeroDocumento: String = "",
        var limiteCredito: BigDecimal = BigDecimal.ZERO,
        var nacionalidad: String = "",
        var clienteCategoriaId: Int? = null,
        var impositivoId: String = "",
        var facturarExtranjero: Byte = 0,
        var bloqueado: Byte = 0,
        var discapacitado: Byte = 0
    ) {
        fun clienteId(clienteId: Long?) = apply { this.clienteId = clienteId }
        fun nombre(nombre: String?) = apply { this.nombre = nombre }
        fun negocioId(negocioId: Int?) = apply { this.negocioId = negocioId }
        fun cuit(cuit: String) = apply { this.cuit = cuit }
        fun razonSocial(razonSocial: String) = apply { this.razonSocial = razonSocial }
        fun nombreFantasia(nombreFantasia: String) = apply { this.nombreFantasia = nombreFantasia }
        fun fechaRestaurant(fechaRestaurant: OffsetDateTime?) = apply { this.fechaRestaurant = fechaRestaurant }
        fun cantidadPaxs(cantidadPaxs: Int) = apply { this.cantidadPaxs = cantidadPaxs }
        fun tipoCliente(tipoCliente: Int) = apply { this.tipoCliente = tipoCliente }
        fun domicilio(domicilio: String) = apply { this.domicilio = domicilio }
        fun telefono(telefono: String) = apply { this.telefono = telefono }
        fun fax(fax: String) = apply { this.fax = fax }
        fun email(email: String) = apply { this.email = email }
        fun numeroMovil(numeroMovil: String) = apply { this.numeroMovil = numeroMovil }
        fun posicionIva(posicionIva: Int) = apply { this.posicionIva = posicionIva }
        fun constante(constante: Int) = apply { this.constante = constante }
        fun documentoId(documentoId: Int) = apply { this.documentoId = documentoId }
        fun tipoDocumento(tipoDocumento: String) = apply { this.tipoDocumento = tipoDocumento }
        fun numeroDocumento(numeroDocumento: String) = apply { this.numeroDocumento = numeroDocumento }
        fun limiteCredito(limiteCredito: BigDecimal) = apply { this.limiteCredito = limiteCredito }
        fun nacionalidad(nacionalidad: String) = apply { this.nacionalidad = nacionalidad }
        fun clienteCategoriaId(clienteCategoriaId: Int?) = apply { this.clienteCategoriaId = clienteCategoriaId }
        fun impositivoId(impositivoId: String) = apply { this.impositivoId = impositivoId }
        fun facturarExtranjero(facturarExtranjero: Byte) = apply { this.facturarExtranjero = facturarExtranjero }
        fun bloqueado(bloqueado: Byte) = apply { this.bloqueado = bloqueado }
        fun discapacitado(discapacitado: Byte) = apply { this.discapacitado = discapacitado }

        fun build() = Cliente(
            clienteId, nombre, negocioId, cuit, razonSocial, nombreFantasia, fechaRestaurant,
            cantidadPaxs, tipoCliente, domicilio, telefono, fax, email, numeroMovil,
            posicionIva, constante, documentoId, tipoDocumento, numeroDocumento,
            limiteCredito, nacionalidad, clienteCategoriaId, impositivoId, facturarExtranjero,
            bloqueado, discapacitado
        )
    }
}
