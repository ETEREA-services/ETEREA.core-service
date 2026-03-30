package eterea.core.service.kotlin.model.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import eterea.core.service.tool.Jsonifier
import java.math.BigDecimal

@JsonIgnoreProperties(ignoreUnknown = true)
data class ParametroDto(

    var parametroId: Long? = null,
    var negocioId: Int? = null,
    var cuentaProveedor: Long? = null,
    var cuentaIva21Compras: Long? = null,
    var cuentaIva27Compras: Long? = null,
    var cuentaIva205Compras: Long? = null,
    var cuentaPercepcionIvaCompra: Long? = null,
    var cuentaPercepcionIngresosBrutosCompra: Long? = null,
    var cuentaGastosNoGravadosCompra: Long? = null,
    var cuentaClientes: Long? = null,
    var cuentaIvaVentas: Long? = null,
    var cuentaIvaRniVentas: Long? = null,
    var cuentaVentas: Long? = null,
    var cuentaCaja: Long? = null,
    var cuentaAjuste: Long? = null,
    var cuentaStockConfirmar: Long? = null,
    var iva1: BigDecimal = BigDecimal.ZERO,
    var iva2: BigDecimal = BigDecimal.ZERO,
    var ivaCredito1: BigDecimal = BigDecimal.ZERO,
    var bloqueIvaCompras: Byte? = null,
    var ivaVenta: BigDecimal = BigDecimal.ZERO,
    var centroStockIdIngreso: Int? = null,
    var centroStockIdRestaurant: Int? = null,
    var facturaElectronicaProduccion: Byte? = null,
    var porcentajeDescuentoPersonal: BigDecimal = BigDecimal.ZERO

) {

    fun jsonify(): String {
        return Jsonifier.builder(this).build();
    }

}
