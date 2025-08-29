package eterea.core.service.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.kotlin.model.Feriado;
import eterea.core.service.service.FeriadoService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping({ "/api/core/feriado", "/feriado" })
public class FeriadoController {

   private final FeriadoService service;

   public FeriadoController(FeriadoService service) {
      this.service = service;
   }

   @GetMapping()
   public ResponseEntity<List<Feriado>> findAll() {
      return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
   }

   @GetMapping("/anio/{anio}")
   public ResponseEntity<List<Feriado>> findByAnio(@PathVariable Integer anio) {
      return ResponseEntity.status(HttpStatus.OK).body(service.findAllByAnio(anio));
   }

   @PostMapping()
   public ResponseEntity<Feriado> save(@RequestBody Feriado feriado) {
      return ResponseEntity.status(HttpStatus.CREATED).body(service.save(feriado));
   }

   @GetMapping("/{fecha}")
   public ResponseEntity<Feriado> findByFecha(@PathVariable LocalDate fecha) {
      return ResponseEntity.status(HttpStatus.OK)
            .body(service.findByFecha(OffsetDateTime.of(fecha, LocalTime.MIN, ZoneOffset.UTC)));
   }

   @DeleteMapping("/{fecha}")
   public ResponseEntity<Void> delete(@PathVariable LocalDate fecha) {
      service.delete(OffsetDateTime.of(fecha, LocalTime.MIN, ZoneOffset.UTC));
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
   }
}
