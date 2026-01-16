package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ReservaArticulo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ReservaArticuloRepository : JpaRepository<ReservaArticulo, Long> {

    fun findAllByReservaId(reservaId: Long): List<ReservaArticulo?>?

    fun findAllByReservaIdAndVoucherId(reservaId: Long, voucherId: Long): List<ReservaArticulo?>?

    fun findByReservaArticuloId(reservaArticuloId: Long): Optional<ReservaArticulo?>?

    fun deleteByReservaArticuloId(reservaArticuloId: Long)

    // Forces EAGER fetch of Articulo
    @Query("SELECT ra FROM ReservaArticulo ra LEFT JOIN FETCH ra.articulo WHERE ra.reservaId = :reservaId")
    fun findAllByReservaIdWithArticulo(@Param("reservaId") reservaId: Long): List<ReservaArticulo>

}