package eterea.core.service.controller;

import java.time.OffsetDateTime;

import org.springframework.http.ResponseEntity;
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
import eterea.core.service.model.dto.ReservaHotelDto;
import eterea.core.service.service.HabitacionMovimientoService;

@RestController
@RequestMapping({ "/api/core/habitacionmovimiento", "/habitacionmovimiento" })
public class HabitacionMovimientoController {

   private final HabitacionMovimientoService service;

   public HabitacionMovimientoController(HabitacionMovimientoService service) {
      this.service = service;
   }

   @GetMapping("/reservada/{nroHabitacion}/{fechaIngreso}/{fechaSalida}")
   public ResponseEntity<Boolean> isHabitacionReservada(@PathVariable Integer nroHabitacion,
         @PathVariable OffsetDateTime fechaIngreso, @PathVariable OffsetDateTime fechaSalida,
         @RequestParam(required = false) Long reservaIdExcluir) {
      return ResponseEntity
            .ok(service.isHabitacionReservada(nroHabitacion, fechaIngreso, fechaSalida, reservaIdExcluir));
   }

   @PostMapping
   public ResponseEntity<HabitacionMovimientoResponseDto> createReservaHabitacion(@RequestBody ReservaHotelDto dto) {
      return ResponseEntity.ok(service.createReservaHabitacion(dto));
   }

   @PutMapping("/{habitacionMovimientoId}/observaciones")
   public ResponseEntity<Void> appendToObservaciones(@PathVariable Long habitacionMovimientoId,
         @RequestBody HabitacionMovimientoObservacionRequestDto dtoObservacion) {
      service.appendToObservaciones(habitacionMovimientoId, dtoObservacion.observacion());
      return ResponseEntity.ok().build();
   }
}
