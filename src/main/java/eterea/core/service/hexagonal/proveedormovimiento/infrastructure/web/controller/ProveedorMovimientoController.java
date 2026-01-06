package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.proveedormovimiento.application.service.ProveedorMovimientoService;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
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
    public ResponseEntity<List<ProveedorMovimiento>> findAllByRegimenInformacionCompras(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime desde,
                                                                                        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime hasta) {
        return ResponseEntity.ok(service.getProveedorMovimientosByRegimenInformacionCompras(desde, hasta));
    }

    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProveedorMovimiento>> findAllByProveedorId(@PathVariable Long proveedorId) {
        return ResponseEntity.ok(service.getProveedorMovimientosByProveedorId(proveedorId));
    }

    @PutMapping("/neto/ajuste/arca/")
    public ResponseEntity<ProveedorMovimiento> ajusteNetoInformacionArca(@RequestBody ProveedorMovimientoNetoAjusteRequest proveedorMovimientoNetoAjusteRequest) {
        return ResponseEntity.ok(service.updateProveedorMovimientoNetoAjuste(proveedorMovimientoNetoAjusteRequest));
    }

    @GetMapping("/resumen/iva/compras/{anho}/{mes}")
    public ResponseEntity<ResumenIvaComprasMensual> resumenIvaComprasMensual(@PathVariable Integer anho, @PathVariable Integer mes) {
        return ResponseEntity.ok(service.getResumenIvaComprasMensual(anho, mes));
    }

}
