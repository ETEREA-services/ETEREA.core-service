package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Voucher
import java.time.OffsetDateTime
import java.util.*
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VoucherRepository : JpaRepository<Voucher, Long> {

        fun findAllByFechaVencimientoAndUsuario(
                fechaVencimiento: OffsetDateTime?,
                usuario: String?
        ): List<Voucher?>?

        fun findAllByFechaServicio(fechaServicio: OffsetDateTime?, sort: Sort?): List<Voucher?>?

        fun findByReservaId(reservaId: Long?): Optional<Voucher?>?

        fun findByVoucherId(voucherId: Long?): Optional<Voucher?>?

        fun findTopByNumeroVoucherContains(numeroVoucher: String): Optional<Voucher?>?

        fun findTopByNumeroVoucherContainsAndFechaTomaAfter(
                numeroVoucher: String,
                fechaToma: OffsetDateTime
        ): Optional<Voucher?>?

        fun findAllByNumeroVoucherIn(numerosVoucher: List<String>): List<Voucher?>?

        @Query(
                """
   	SELECT
   		v
   	FROM
   		Voucher v
   	WHERE
   		LOWER(v.nombrePax) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
   		AND
   		v.fechaServicio BETWEEN :desde AND :hasta
   	ORDER BY v.nombrePax
   	"""
        )
        fun findByNombrePaxContainingIgnoreCaseAndFechaServicioBetween(
                searchTerm: String,
                desde: OffsetDateTime,
                hasta: OffsetDateTime
        ): List<Voucher?>?
}
