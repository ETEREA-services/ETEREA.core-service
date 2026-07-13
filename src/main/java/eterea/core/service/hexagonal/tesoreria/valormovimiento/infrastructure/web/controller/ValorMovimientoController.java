package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.application.service.ValorMovimientoService;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.dto.ValorMovimientoResponse;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.mapper.ValorMovimientoDtoMapper;

@RestController
@RequestMapping({"/api/v1/valor-movimientos", "/api/core/valorMovimiento", "/valorMovimiento"})
@RequiredArgsConstructor
public class ValorMovimientoController {

   private final ValorMovimientoService service;
   private final ValorMovimientoDtoMapper mapper;

   @GetMapping()
   public ResponseEntity<List<ValorMovimientoResponse>> findAllValorMovimientos(
         @RequestParam LocalDate desde,
         @RequestParam LocalDate hasta,
         @RequestParam boolean cierreCajaOnly,
         @RequestParam boolean ingresosOnly) {
      List<ValorMovimientoResponse> responses = service.findAllMovimientos(desde, hasta, cierreCajaOnly,
                  ingresosOnly).stream()
              .map(mapper::toResponse)
              .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.OK).body(responses);
   }

}
