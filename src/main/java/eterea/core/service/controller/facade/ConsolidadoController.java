package eterea.core.service.controller.facade;

import eterea.core.service.service.facade.ConsolidadoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping({"/api/core/consolidado", "/consolidado"})
@Slf4j
public class ConsolidadoController {

    private final ConsolidadoService service;

    public ConsolidadoController(ConsolidadoService service) {
        this.service = service;
    }

    @GetMapping("/faltantes/fecha/{fecha}")
    public ResponseEntity<String> fillFaltantesFecha(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fecha) {
        log.debug("Processing ConsolidadoController.fillFaltantesFecha with fecha: {}", fecha);
        return ResponseEntity.ok(service.fillFaltantesFecha(fecha));
    }

}
