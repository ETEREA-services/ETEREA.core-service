package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Voucher
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.*

@Repository
interface VoucherRepository : JpaRepository<Voucher, Long> {

    fun findAllByFechaVencimientoAndUsuario(fechaVencimiento: OffsetDateTime?, usuario: String?): List<Voucher?>?

    fun findAllByFechaServicio(fechaServicio: OffsetDateTime?, sort: Sort?): List<Voucher?>?

    fun findByReservaId(reservaId: Long?): Optional<Voucher?>?

    fun findByVoucherId(voucherId: Long?): Optional<Voucher?>?
    fun findTopByNumeroVoucherContains(numeroVoucher: String): Optional<Voucher?>?

}