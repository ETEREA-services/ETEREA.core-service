package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ComprobanteFaltante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import java.time.OffsetDateTime

interface ComprobanteFaltanteRepository : JpaRepository<ComprobanteFaltante, Long> {

    @Modifying
    @Query("delete from ComprobanteFaltante where fecha = :fecha")
    fun deleteAllByFecha(fecha: OffsetDateTime)

}