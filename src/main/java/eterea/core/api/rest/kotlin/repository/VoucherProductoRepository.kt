package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.VoucherProducto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface VoucherProductoRepository : JpaRepository<VoucherProducto, Long> {
    fun findAllByVoucherId(voucherId: Long): List<VoucherProducto?>?

    fun findByVoucherProductoId(voucherProductoId: Long): Optional<VoucherProducto?>?

    fun deleteAllByVoucherId(voucherId: Long)

}