package eterea.core.service.controller;

import eterea.core.service.kotlin.model.MonedaCotizacion;
import eterea.core.service.service.MonedaCotizacionService;
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
public class MonedaCotizacionController {

    private final MonedaCotizacionService service;

    public MonedaCotizacionController(MonedaCotizacionService service) {
        this.service = service;
    }

    @GetMapping("/periodo/{monedaId}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<List<MonedaCotizacion>> finAllPeriodoCotizacion(@PathVariable Integer monedaId,
                                                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaDesde,
                                                                    @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaHasta) {
        return ResponseEntity.ok(service.findAllPeriodoCotizacion(monedaId, fechaDesde, fechaHasta));
    }

    @GetMapping("/fill/{monedaId}/{fechaDesde}/{fechaHasta}")
    public ResponseEntity<List<MonedaCotizacion>> fillCotizacion(@PathVariable Integer monedaId,
                                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaDesde,
                                                                 @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fechaHasta) {
        return ResponseEntity.ok(service.fillCotizacion(monedaId, fechaDesde, fechaHasta));
    }

}
