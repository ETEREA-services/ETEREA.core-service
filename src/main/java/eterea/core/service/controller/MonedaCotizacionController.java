package eterea.core.service.controller;

import eterea.core.service.kotlin.model.MonedaCotizacion;
import eterea.core.service.service.MonedaCotizacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping({"/api/core/monedaCotizacion", "/monedaCotizacion"})
@RequiredArgsConstructor
public class MonedaCotizacionController {

    private final MonedaCotizacionService service;

    @GetMapping("/periodo/{monedaIdOrigen}/{monedaIdDestino}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<List<MonedaCotizacion>> findAllPeriodoCotizacion(@PathVariable Integer monedaIdOrigen,
                                                                          @PathVariable Integer monedaIdDestino,
                                                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaDesde,
                                                                          @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaHasta) {
        return ResponseEntity.ok(service.findAllPeriodoCotizacion(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta));
    }

    @GetMapping("/fill/{monedaIdOrigen}/{monedaIdDestino}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<List<MonedaCotizacion>> fillCotizacion(@PathVariable Integer monedaIdOrigen,
                                                                 @PathVariable Integer monedaIdDestino,
                                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaDesde,
                                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaHasta) {
        return ResponseEntity.ok(service.fillCotizacion(monedaIdOrigen, monedaIdDestino, fechaDesde, fechaHasta));
    }

}
