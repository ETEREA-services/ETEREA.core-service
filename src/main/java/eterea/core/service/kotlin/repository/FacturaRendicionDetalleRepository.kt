package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.FacturaRendicionDetalle
import org.springframework.data.jpa.repository.JpaRepository

interface FacturaRendicionDetalleRepository : JpaRepository<FacturaRendicionDetalle, Long> {

    fun findAllByProveedorId(proveedorId: Long): List<FacturaRendicionDetalle?>?

}