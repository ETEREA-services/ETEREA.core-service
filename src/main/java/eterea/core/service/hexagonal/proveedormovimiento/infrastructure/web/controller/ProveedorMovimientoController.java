package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import eterea.core.service.hexagonal.proveedormovimiento.application.service.ProveedorMovimientoService;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ProveedorMovimientoNetoAjusteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping({"/api/core/proveedormovimiento", "/proveedormovimiento"})
@RequiredArgsConstructor
public class ProveedorMovimientoController {

    private final ProveedorMovimientoService service;

    @GetMapping("/arca/regimen/informacion/compras/{desde}/{hasta}")
    public ResponseEntity<List<ProveedorMovimientoEntity>> findAllByRegimenInformacionCompras(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
                                                                                              @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta) {
        return ResponseEntity.ok(service.findAllByRegimenInformacionCompras(desde, hasta));
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProveedorMovimientoEntity>> findAllByProveedorId(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(service.findAllByProveedorId(proveedorId));
    }

    @PutMapping("/neto/ajuste/arca/")
    public ResponseEntity<ProveedorMovimientoEntity> ajusteNetoInformacionArca(@RequestBody ProveedorMovimientoNetoAjusteRequest proveedorMovimientoNetoAjusteRequest) {
        return ResponseEntity.ok(service.ajusteNetoInformacionArca(proveedorMovimientoNetoAjusteRequest));
    }

}
