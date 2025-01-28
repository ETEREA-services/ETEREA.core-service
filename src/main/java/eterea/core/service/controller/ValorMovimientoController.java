package eterea.core.service.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eterea.core.service.model.dto.CobroInternoDto;
import eterea.core.service.service.ValorMovimientoService;

@RestController
@RequestMapping("/api/v1/valor-movimientos")
public class ValorMovimientoController {
   private final ValorMovimientoService valorMovimientoService;

   public ValorMovimientoController(ValorMovimientoService valorMovimientoService) {
      this.valorMovimientoService = valorMovimientoService;
   }

   // @GetMapping()
   // public ResponseEntity<List<ValorMovimientoDto>> findAllValorMovimientos(
   //       @RequestParam LocalDate desde,
   //       @RequestParam LocalDate hasta,
   //       @RequestParam boolean cierreCajaOnly,
   //       @RequestParam boolean ingresosOnly) {
   //    return ResponseEntity.status(HttpStatus.OK)
   //          .body(valorMovimientoService.findAllMovimientos(desde, hasta, cierreCajaOnly,
   //                ingresosOnly));
   // }

   @GetMapping("/cobros-internos")
   public ResponseEntity<List<CobroInternoDto>> findAllCobroInternos(
         @RequestParam LocalDate desde,
         @RequestParam LocalDate hasta) {
      return ResponseEntity.status(HttpStatus.OK)
            .body(valorMovimientoService.findAllCobroInterno(desde, hasta));
   }
}