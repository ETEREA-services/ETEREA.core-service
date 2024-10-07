package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.Comprobante
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface ComprobanteRepository : JpaRepository<Comprobante, Int> {

    fun findAllByFacturaElectronicaAndAsociado(facturaElectronica: Byte?, Asociado: Byte?): List<Comprobante?>?

    fun findAllByModuloAndTransferirAndInvisible(modulo: Int?, transferir: Byte?, invisible: Byte?): List<Comprobante?>?

    fun findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(
        modulo: Int?,
        transferir: Byte?, invisible: Byte?, debita: Byte?, comprobanteId: Int?
    ): List<Comprobante?>?

    fun findAllByModuloAndTransferirAndInvisibleAndDebita(
        modulo: Int?, transferir: Byte?,
        invisible: Byte?, debita: Byte?
    ): List<Comprobante?>?

    fun findAllByModuloAndTransferirAndInvisibleAndComprobanteId(
        modulo: Int?, transferir: Byte?,
        invisible: Byte?, comprobanteId: Int?
    ): List<Comprobante?>?

    fun findAllByModuloAndDebitaAndAsociado(modulo: Int?, debita: Byte?, asociado: Byte?): List<Comprobante?>?

    fun findByComprobanteId(comprobanteId: Int?): Optional<Comprobante?>?

    fun findFirstByOrderByComprobanteId(): Optional<Comprobante?>?
    fun findFirstByOrderByComprobanteIdDesc(): Optional<Comprobante?>?

}
