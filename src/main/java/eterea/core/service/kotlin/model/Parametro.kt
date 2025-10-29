package eterea.core.service.kotlin.model

import eterea.core.service.model.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "parametros")
data class Parametro(

    @Id @Column(name = "clave")
    var parametroId: Long? = null,

    @Column(name = "par_neg_id")
    var negocioId: Int? = null,

    @Column(name = "ctaprov")
    var cuentaProveedor: Long? = null,

    @Column(name = "ctaiva21compras")
    var cuentaIva21Compras: Long? = null,

    @Column(name = "ctaiva27compras")
    var cuentaIva27Compras: Long? = null,

    @Column(name = "ctaiva105compras")
    var cuentaIva205Compras: Long? = null,

    @Column(name = "ctaperivacompras")
    var cuentaPercepcionIvaCompra: Long? = null,

    @Column(name = "ctaperingbrutoscompras")
    var cuentaPercepcionIngresosBrutosCompra: Long? = null,

    @Column(name = "ctagngcompras")
    var cuentaGastosNoGravadosCompra: Long? = null,

    @Column(name = "ctaclientes")
    var cuentaClientes: Long? = null,

    @Column(name = "ctaivaventas")
    var cuentaIvaVentas: Long? = null,

    @Column(name = "ctaivarniventas")
    var cuentaIvaRniVentas: Long? = null,

    @Column(name = "ctaventas")
    var cuentaVentas: Long? = null,

    @Column(name = "ctacaja")
    var cuentaCaja: Long? = null,

    @Column(name = "ctaajuste")
    var cuentaAjuste: Long? = null,

    var cuentaStockConfirmar: Long? = null,
    var iva1: BigDecimal = BigDecimal.ZERO,
    var iva2: BigDecimal = BigDecimal.ZERO,

    @Column(name = "ivacre1")
    var ivaCredito1: BigDecimal = BigDecimal.ZERO,

    @Column(name = "bloqueoivacompras")
    var bloqueIvaCompras: Byte? = null,

    @Column(name = "ivavta")
    var ivaVenta: BigDecimal = BigDecimal.ZERO,

    @Column(name = "par_cst_id_ingreso")
    var centroStockIdIngreso: Int? = null,

    @Column(name = "par_cst_id_restaurant")
    var centroStockIdRestaurant: Int? = null,

    @Column(name = "par_feproduccion")
    var facturaElectronicaProduccion: Byte? = null,

    var porcentajeDescuentoPersonal: BigDecimal = BigDecimal.ZERO

) : Auditable()
