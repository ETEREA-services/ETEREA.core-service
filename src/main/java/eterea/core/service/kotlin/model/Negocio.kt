package eterea.core.service.kotlin.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.json.JsonMapper
import eterea.core.service.model.Auditable
import eterea.core.service.tool.Jsonifier
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
    var hasGateway: Byte = 0,
    var copyArticulo: Byte = 0,

    @Transient
    var ipAddress: String? = null,

    @Transient
    var backendServer: String? = null

) : Auditable() {

    fun jsonify(): String {
        return Jsonifier.builder(this).build()
    }

}
