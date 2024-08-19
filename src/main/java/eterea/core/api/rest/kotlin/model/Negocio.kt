package eterea.core.api.rest.kotlin.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Negocio(

    @Id
    @Column(name = "neg_id")
    var negocioId: Int? = null,

    @Column(name = "neg_nombre")
    var nombre: String = "",

    @Column(name = "neg_id_real")
    var negocioIdReal: Int = 0,

    @Column(name = "neg_ip")
    var databaseIpVpn: String = "",

    var databaseIpLan: String = "",

    @Column(name = "neg_db")
    var database: String? = null,

    @Column(name = "neg_user")
    var user: String? = null,

    @Column(name = "neg_transfstock")
    var transferenciaStock: Byte = 0,

    @Column(name = "neg_transfvalor")
    var transferenciaValor: Byte = 0,

    @Column(name = "backend_server")
    var backendIpVpn: String = "",

    var backendIpLan: String = "",
    var backendPort: String = "",
    var facturaServer: String = "",
    var facturaPort: String = "",
    var hasGateway: Byte = 0

) : Auditable()
