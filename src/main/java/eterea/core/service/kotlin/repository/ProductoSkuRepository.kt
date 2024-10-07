package eterea.core.service.kotlin.repository

import eterea.core.service.kotlin.model.ProductoSku
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ProductoSkuRepository : JpaRepository<ProductoSku, Long> {

    fun findBySkuAndLunes(sku: String, lunes: Byte): Optional<ProductoSku?>?

    fun findBySkuAndMartes(sku: String, martes: Byte): Optional<ProductoSku?>?

    fun findBySkuAndMiercoles(sku: String, miercoles: Byte): Optional<ProductoSku?>?

    fun findBySkuAndJueves(sku: String, jueves: Byte): Optional<ProductoSku?>?

    fun findBySkuAndViernes(sku: String, viernes: Byte): Optional<ProductoSku?>?

    fun findBySkuAndSabado(sku: String, sabado: Byte): Optional<ProductoSku?>?

    fun findBySkuAndDomingo(sku: String, domingo: Byte): Optional<ProductoSku?>?

    fun findBySkuAndFeriado(sku: String, feriado: Byte): Optional<ProductoSku?>?

}