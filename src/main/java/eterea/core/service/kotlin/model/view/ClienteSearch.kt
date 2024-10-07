package eterea.core.service.kotlin.model.view

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.Immutable
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Immutable
@Table(name = "vw_clienteactivado")
data class ClienteSearch(

    @Id
    @Column(name = "codigo")
    var clienteId: Long? = null,

    @Column(name = "nombre")
    var nombre: String? = null,

    @Column(name = "cli_neg_id")
    var negocioId: Int? = null,

    @Column(name = "cuit")
    var cuit: String = "",

    @Column(name = "razon")
    var razonSocial: String = "",

    @Column(name = "cli_fantasia")
    var nombreFantasia: String? = null,

    @Column(name = "cli_fecharestaurant")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaRestaurant: OffsetDateTime? = null,

    @Column(name = "cli_cantpaxs")
    var cantidadPaxs: Int = 0,

    @Column(name = "cli_tipo")
    var tipoCliente: Int = 0,

    @Column(name = "domicilio")
    var domicilio: String = "",

    @Column(name = "tel")
    var telefono: String = "",

    @Column(name = "fax")
    var fax: String? = null,

    @Column(name = "email")
    var email: String = "",

    @Column(name = "celular")
    var numeroMovil: String? = null,

    @Column(name = "posicion")
    var posicionIva: Int = 0,

    @Column(name = "constante")
    var constante: Int = 0,

    @Column(name = "documento_id")
    var documentoId: Int = 0,

    @Column(name = "tipodoc")
    var tipoDocumento: String = "",

    @Column(name = "nrodoc")
    var numeroDocumento: String? = null,

    @Column(name = "limitecredito")
    var limiteCredito: BigDecimal = BigDecimal.ZERO,

    @Column(name = "nacionalidad")
    var nacionalidad: String? = null,

    @Column(name = "cli_cca_id")
    var clienteCategoriaId: Int? = null,

    @Column(name = "cli_idimpositivo")
    var impositivoId: String = "",

    @Column(name = "facturarextranjero")
    var facturarExtranjero: Byte = 0,

    @Column(name = "bloqueado")
    var bloqueado: Byte = 0,

    @Column(name = "discapacitado")
    var discapacitado: Byte = 0,

    @Column(name = "search")
    var search: String? = null
    
)