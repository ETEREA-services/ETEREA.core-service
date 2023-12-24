package eterea.core.api.rest.kotlin.extern

data class InformacionPagador(

    var orderNumberId: Long? = null,
    var eMail: String? = "",
    var nombre: String = "",
    var numeroDocumento: String = "",
    var telefono: String? = null,
    var tipoDocumento: String = ""

)
