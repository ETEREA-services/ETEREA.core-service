package eterea.core.service.controller;

import eterea.core.service.model.Track;
import eterea.core.service.service.TrackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/track")
@RequiredArgsConstructor
public class TrackController {

    private final TrackService service;

    @GetMapping("/start/{descripcion}")
    public ResponseEntity<Track> startTracking(@PathVariable String descripcion) {
        return ResponseEntity.ok(service.startTracking(descripcion));
    }

}
