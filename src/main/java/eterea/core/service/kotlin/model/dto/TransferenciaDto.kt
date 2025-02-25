package eterea.core.service.kotlin.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import eterea.core.service.kotlin.model.Transferencia
import java.math.BigDecimal
import java.time.OffsetDateTime

data class TransferenciaDto(
    var transferenciaId: Long? = null,
    var negocioIdDesde: Int? = null,
    var negocioIdHasta: Int? = null,
    var numeroTransferencia: Long? = null,
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fecha: OffsetDateTime? = null,
    
    var total: BigDecimal = BigDecimal.ZERO,
    var ordenContable: Int? = null,
    var transferido: Byte = 0,
    var comprobanteId: Int? = null,
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ", timezone = "UTC")
    var fechaOtro: OffsetDateTime? = null,

    var comprobante: ComprobanteDto? = null
) {
    class Builder {
        private var transferenciaId: Long? = null
        private var negocioIdDesde: Int? = null
        private var negocioIdHasta: Int? = null
        private var numeroTransferencia: Long? = null
        private var fecha: OffsetDateTime? = null
        private var total: BigDecimal = BigDecimal.ZERO
        private var ordenContable: Int? = null
        private var transferido: Byte = 0
        private var comprobanteId: Int? = null
        private var fechaOtro: OffsetDateTime? = null
        private var comprobante: ComprobanteDto? = null

        fun transferenciaId(id: Long?) = apply { this.transferenciaId = id }
        fun negocioIdDesde(id: Int?) = apply { this.negocioIdDesde = id }
        fun negocioIdHasta(id: Int?) = apply { this.negocioIdHasta = id }
        fun numeroTransferencia(numero: Long?) = apply { this.numeroTransferencia = numero }
        fun fecha(fecha: OffsetDateTime?) = apply { this.fecha = fecha }
        fun total(total: BigDecimal) = apply { this.total = total }
        fun ordenContable(orden: Int?) = apply { this.ordenContable = orden }
        fun transferido(transferido: Byte) = apply { this.transferido = transferido }
        fun comprobanteId(id: Int?) = apply { this.comprobanteId = id }
        fun fechaOtro(fecha: OffsetDateTime?) = apply { this.fechaOtro = fecha }
        fun comprobante(comprobante: ComprobanteDto?) = apply { this.comprobante = comprobante }

        fun build() = TransferenciaDto(
            transferenciaId,
            negocioIdDesde,
            negocioIdHasta,
            numeroTransferencia,
            fecha,
            total,
            ordenContable,
            transferido,
            comprobanteId,
            fechaOtro,
            comprobante
        )
    }

    companion object {
        fun builder() = Builder()
        
        fun fromEntity(transferencia: Transferencia) = TransferenciaDto(
            transferenciaId = transferencia.transferenciaId,
            negocioIdDesde = transferencia.negocioIdDesde,
            negocioIdHasta = transferencia.negocioIdHasta,
            numeroTransferencia = transferencia.numeroTransferencia,
            fecha = transferencia.fecha,
            total = transferencia.total,
            ordenContable = transferencia.ordenContable,
            transferido = transferencia.transferido,
            comprobanteId = transferencia.comprobanteId,
            fechaOtro = transferencia.fechaOtro,
            comprobante = transferencia.comprobante?.let { ComprobanteDto.fromEntity(it) }
        )
    }

    fun toEntity() = Transferencia(
        transferenciaId = this.transferenciaId,
        negocioIdDesde = this.negocioIdDesde,
        negocioIdHasta = this.negocioIdHasta,
        numeroTransferencia = this.numeroTransferencia,
        fecha = this.fecha,
        total = this.total,
        ordenContable = this.ordenContable,
        transferido = this.transferido,
        comprobanteId = this.comprobanteId,
        fechaOtro = this.fechaOtro,
        comprobante = this.comprobante?.toEntity()
    )
}
