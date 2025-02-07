package eterea.core.service.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.dto.programadia.ProgramaDiaDetallesDto;
import eterea.core.service.service.ProgramaDiaService;

@RestController
@RequestMapping({ "/api/core/programadia", "/programadia" })
public class ProgramaDiaController {

   private final ProgramaDiaService programaDiaService;

   public ProgramaDiaController(ProgramaDiaService programaDiaService) {
      this.programaDiaService = programaDiaService;
   }

   @GetMapping("/{fechaServicio}")
   public ResponseEntity<List<ProgramaDiaDetallesDto>> getProgramaDiaDetalles(@PathVariable @DateTimeFormat(iso = ISO.DATE_TIME) OffsetDateTime fecha) {
      return new ResponseEntity<>(programaDiaService.getProgramaDiaDetalles(fecha), HttpStatus.OK);
   }
}
