package eterea.core.service.kotlin.model

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
@Table(name = "movhabitaciones")
data class HabitacionMovimiento(
        @Id @Column(name = "Clave") var habitacionMovimientoId: Long? = null,
        @Column(name = "NroReserva") var numeroReserva: Long? = null,
        @Column(name = "fechaingreso")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
                timezone = "UTC"
        )
        var fechaIngreso: OffsetDateTime? = null,
        @Column(name = "fechasalida")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
                timezone = "UTC"
        )
        var fechaSalida: OffsetDateTime? = null,
        @Column(name = "cgotarifa") var tarifaId: Long? = null,
        @Column(name = "conceptotarifa") var conceptoTarifa: String? = null,
        @Column(name = "preciounitariotarifa")
        var precioUnitarioTarifa: BigDecimal? = BigDecimal.ZERO,
        @Column(name = "cantidadpax") var cantidadPax: Long? = 0L,
        @Column(name = "diasfacturados") var diasFacturados: Long? = 0L,
        @Column(name = "importefacturado") var importeFacturado: BigDecimal? = BigDecimal.ZERO,
        @Column(name = "fechaoperacion")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
                timezone = "UTC"
        )
        var fechaOperacion: OffsetDateTime? = null,
        @Column(name = "fechavto")
        @JsonFormat(
                shape = JsonFormat.Shape.STRING,
                pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
                timezone = "UTC"
        )
        var fechaVencimiento: OffsetDateTime? = null,
        @Column(name = "item") var item: Long? = 0L,
        @Column(name = "tarifastandard") var tarifaStandard: Byte? = 0,
        @Column(name = "mha_paxmay") var cantidadPaxMayor: Int? = 0,
        @Column(name = "mha_paxmen") var cantidadPaxMenor: Int? = 0,
        @Column(name = "mha_observ") var observaciones: String? = "",
        @ManyToOne() @JoinColumn(name = "cgocliente") var cliente: Cliente? = null,
        @ManyToOne() @JoinColumn(name = "cgoestadores") var estadoReserva: Comprobante? = null,
        @ManyToOne() @JoinColumn(name = "cgohabitacion") var habitacion: Habitacion? = null,
) : Auditable() {
   data class Builder(
           var habitacionMovimientoId: Long? = null,
           var numeroReserva: Long? = null,
           var fechaIngreso: OffsetDateTime? = null,
           var fechaSalida: OffsetDateTime? = null,
           var tarifaId: Long? = null,
           var conceptoTarifa: String? = null,
           var precioUnitarioTarifa: BigDecimal? = BigDecimal.ZERO,
           var cantidadPax: Long? = 0L,
           var diasFacturados: Long? = 0L,
           var importeFacturado: BigDecimal? = BigDecimal.ZERO,
           var fechaOperacion: OffsetDateTime? = null,
           var fechaVencimiento: OffsetDateTime? = null,
           var item: Long? = 0L,
           var tarifaStandard: Byte? = 0,
           var cantidadPaxMayor: Int? = 0,
           var cantidadPaxMenor: Int? = 0,
           var observaciones: String? = "",
           var cliente: Cliente? = null,
           var estadoReserva: Comprobante? = null,
           var habitacion: Habitacion? = null,
   ) {
      fun habitacionMovimientoId(habitacionMovimientoId: Long?) = apply {
         this.habitacionMovimientoId = habitacionMovimientoId
      }
      fun numeroReserva(numeroReserva: Long?) = apply { this.numeroReserva = numeroReserva }
      fun fechaIngreso(fechaIngreso: OffsetDateTime?) = apply { this.fechaIngreso = fechaIngreso }
      fun fechaSalida(fechaSalida: OffsetDateTime?) = apply { this.fechaSalida = fechaSalida }
      fun tarifaId(tarifaId: Long?) = apply { this.tarifaId = tarifaId }
      fun conceptoTarifa(conceptoTarifa: String?) = apply { this.conceptoTarifa = conceptoTarifa }
      fun precioUnitarioTarifa(precioUnitarioTarifa: BigDecimal?) = apply {
         this.precioUnitarioTarifa = precioUnitarioTarifa
      }
      fun cantidadPax(cantidadPax: Long?) = apply { this.cantidadPax = cantidadPax }
      fun diasFacturados(diasFacturados: Long?) = apply { this.diasFacturados = diasFacturados }
      fun importeFacturado(importeFacturado: BigDecimal?) = apply {
         this.importeFacturado = importeFacturado
      }
      fun fechaOperacion(fechaOperacion: OffsetDateTime?) = apply {
         this.fechaOperacion = fechaOperacion
      }
      fun fechaVencimiento(fechaVencimiento: OffsetDateTime?) = apply {
         this.fechaVencimiento = fechaVencimiento
      }
      fun item(item: Long?) = apply { this.item = item }
      fun tarifaStandard(tarifaStandard: Byte?) = apply { this.tarifaStandard = tarifaStandard }
      fun cantidadPaxMayor(cantidadPaxMayor: Int?) = apply {
         this.cantidadPaxMayor = cantidadPaxMayor
      }
      fun cantidadPaxMenor(cantidadPaxMenor: Int?) = apply {
         this.cantidadPaxMenor = cantidadPaxMenor
      }
      fun observaciones(observaciones: String?) = apply { this.observaciones = observaciones }
      fun cliente(cliente: Cliente?) = apply { this.cliente = cliente }
      fun estadoReserva(estadoReserva: Comprobante?) = apply { this.estadoReserva = estadoReserva }
      fun habitacion(habitacion: Habitacion?) = apply { this.habitacion = habitacion }

      fun build() =
              HabitacionMovimiento(
                      habitacionMovimientoId,
                      numeroReserva,
                      fechaIngreso,
                      fechaSalida,
                      tarifaId,
                      conceptoTarifa,
                      precioUnitarioTarifa,
                      cantidadPax,
                      diasFacturados,
                      importeFacturado,
                      fechaOperacion,
                      fechaVencimiento,
                      item,
                      tarifaStandard,
                      cantidadPaxMayor,
                      cantidadPaxMenor,
                      observaciones,
                      cliente,
                      estadoReserva,
                      habitacion
              )
   }
}
