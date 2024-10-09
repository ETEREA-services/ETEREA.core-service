package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ProveedorMovimiento
import org.springframework.data.jpa.repository.JpaRepository

interface ProveedorMovimientoRepository : JpaRepository<ProveedorMovimiento, Long?> {

    fun findAllByProveedorIdOrderByFechaComprobante(proveedorId: Long): List<ProveedorMovimiento?>?

}