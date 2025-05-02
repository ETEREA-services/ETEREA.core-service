package eterea.core.service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

   @PostMapping
   public ResponseEntity<HabitacionMovimientoResponseDto> createReservaHabitacion(@RequestBody ReservaHotelDto dto) {
      return ResponseEntity.ok(service.createReservaHabitacion(dto));
   }
}
