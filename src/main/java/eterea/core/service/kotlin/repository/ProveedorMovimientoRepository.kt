package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ProveedorMovimiento
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import java.time.OffsetDateTime

interface ProveedorMovimientoRepository : JpaRepository<ProveedorMovimiento, Long?> {

    fun findAllByProveedorIdOrderByFechaComprobante(proveedorId: Long): List<ProveedorMovimiento?>?
    fun findAllByFechaContableBetweenAndComprobanteLibroIva(
        desde: OffsetDateTime,
        hasta: OffsetDateTime,
        libroIva: Byte,
        sort: Sort
    ): List<ProveedorMovimiento>

}