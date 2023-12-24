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

) : Auditable()
