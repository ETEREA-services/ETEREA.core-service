package eterea.core.service.controller;

import eterea.core.service.service.SnapshotService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/core/snapshot")
public class SnapshotController {

    private final SnapshotService service;

    public SnapshotController(SnapshotService service) {
        this.service = service;
    }

}
