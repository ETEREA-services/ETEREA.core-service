package eterea.core.api.rest.kotlin.repository

import eterea.core.api.rest.kotlin.model.Feriado
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.OffsetDateTime
import java.util.Optional

@Repository
interface FeriadoRepository : JpaRepository<Feriado, OffsetDateTime> {

    fun findByFecha(fecha: OffsetDateTime?): Optional<Feriado?>?

}