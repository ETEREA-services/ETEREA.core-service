package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.ArticuloSaldoFecha
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.math.BigDecimal
import java.time.OffsetDateTime


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

}