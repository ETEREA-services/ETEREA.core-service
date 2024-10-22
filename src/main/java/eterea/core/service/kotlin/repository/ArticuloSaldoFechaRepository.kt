package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ArticuloSaldoFecha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.math.BigDecimal
import java.time.OffsetDateTime
import java.util.Optional


interface ArticuloSaldoFechaRepository : JpaRepository<ArticuloSaldoFecha, Long> {

    @Query(
        value = ("SELECT COALESCE(SUM(asf_saldo), 0) FROM articulossaldofecha " +
                "WHERE asf_cst_id = :centroStockId " +
                "AND asf_art_id = :articuloId " +
                "AND asf_fecha <= :tope"), nativeQuery = true
    )
    fun calculateSaldo(
        @Param("centroStockId") centroStockId: Int?,
        @Param("articuloId") articuloId: String?,
        @Param("tope") tope: OffsetDateTime?
    ): BigDecimal?

    fun findAllByCentroStockIdAndFechaAndArticuloIdIn(
        centroStockId: Int,
        fechaMovimiento: OffsetDateTime,
        articuloIds: MutableList<String>
    ): List<ArticuloSaldoFecha>

    fun findByCentroStockIdAndArticuloIdAndFecha(
        centroStockId: Int,
        articuloId: String,
        fechaMovimiento: OffsetDateTime
    ): Optional<ArticuloSaldoFecha>


}