package eterea.core.service.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.dto.HabitacionMovimientoObservacionRequestDto;
import eterea.core.service.model.dto.HabitacionMovimientoResponseDto;
import eterea.core.service.kotlin.model.Habitacion;
import eterea.core.service.model.dto.CreateHabitacionMovimientoDto;
import eterea.core.service.service.HabitacionMovimientoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping({ "/api/core/habitacionmovimiento", "/habitacionmovimiento" })
public class HabitacionMovimientoController {

   private final HabitacionMovimientoService service;

   public HabitacionMovimientoController(HabitacionMovimientoService service) {
      this.service = service;
   }

   @GetMapping("/{numeroReserva}")
   public ResponseEntity<HabitacionMovimientoResponseDto> getHabitacionMovimiento(@PathVariable Long numeroReserva) {
      return ResponseEntity.ok(service.findByNumeroReserva(numeroReserva));
   }

   @GetMapping("/reservada/{nroHabitacion}/{fechaIngreso}/{fechaSalida}")
   public ResponseEntity<Boolean> isHabitacionReservada(@PathVariable Integer nroHabitacion,
         @PathVariable OffsetDateTime fechaIngreso, @PathVariable OffsetDateTime fechaSalida,
         @RequestParam(required = false) Long reservaIdExcluir) {
      return ResponseEntity
            .ok(service.isHabitacionReservada(nroHabitacion, fechaIngreso, fechaSalida, reservaIdExcluir));
   }

   @PostMapping
   public ResponseEntity<HabitacionMovimientoResponseDto> createReservaHabitacion(@RequestBody @Valid CreateHabitacionMovimientoDto dto) {
      return ResponseEntity.ok(service.createReservaHabitacion(dto));
   }

   @PutMapping("/{numeroReserva}")
   public ResponseEntity<HabitacionMovimientoResponseDto> updateReservaHabitacion(@PathVariable Long numeroReserva,
         @RequestBody @Valid CreateHabitacionMovimientoDto dto) {
      return ResponseEntity.ok(service.updateReservaHabitacion(numeroReserva, dto));
   }


   @PutMapping("/{habitacionMovimientoId}/observaciones")
   public ResponseEntity<Void> appendToObservaciones(@PathVariable Long habitacionMovimientoId,
         @RequestBody HabitacionMovimientoObservacionRequestDto dtoObservacion) {
      service.appendToObservaciones(habitacionMovimientoId, dtoObservacion.observacion());
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{numeroReserva}")
   public ResponseEntity<Void> delete(@PathVariable Long numeroReserva) {
      service.delete(numeroReserva);
      return ResponseEntity.ok().build();
   }

   @GetMapping("/habitaciones/disponibles")
   public ResponseEntity<List<Habitacion>> getHabitacionesDisponibles(
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
            @RequestParam(required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta,
            @RequestParam(required = false) Long habitacionMovimientoIdExcluir) {
      return ResponseEntity.ok(service.getHabitacionesDisponibles(desde, hasta, habitacionMovimientoIdExcluir));
   }

}
