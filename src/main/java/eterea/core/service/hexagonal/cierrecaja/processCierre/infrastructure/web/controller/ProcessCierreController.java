package eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.web.controller;

import eterea.core.service.hexagonal.cierrecaja.processCierre.application.service.ProcessCierreService;
import eterea.core.service.hexagonal.cierrecaja.processCierre.domain.model.PendingCounts;
import eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.web.dto.PendingCountsResponse;
import eterea.core.service.hexagonal.cierrecaja.processCierre.infrastructure.web.mapper.PendingCountsDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;

@RestController
@RequestMapping({"/api/core/cierrecaja/processcierre", "/cierrecaja/processcierre"})
@RequiredArgsConstructor
public class ProcessCierreController {

    private final ProcessCierreService service;
    private final PendingCountsDtoMapper mapper;

    @GetMapping("/pending-counts/{desde}/{hasta}")
    public ResponseEntity<PendingCountsResponse> getPendingCounts(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta) {
        PendingCounts pendingCounts = service.getPendingCounts(desde, hasta);
        return ResponseEntity.ok(mapper.toResponse(pendingCounts));
    }

}
