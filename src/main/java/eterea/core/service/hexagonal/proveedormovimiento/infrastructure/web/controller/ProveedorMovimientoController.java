package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.controller;

import eterea.core.service.hexagonal.proveedormovimiento.application.service.ProveedorMovimientoService;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ProveedorMovimientoNetoAjusteRequest;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ResumenIvaComprasMensualPosicionResponse;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ResumenIvaComprasMensualResponse;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.mapper.ResumenIvaComprasMensualDtoMapper;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.mapper.ResumenIvaComprasMensualPosicionDtoMapper;
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
    private final ResumenIvaComprasMensualDtoMapper resumenIvaComprasMensualDtoMapper;
    private final ResumenIvaComprasMensualPosicionDtoMapper resumenIvaComprasMensualPosicionDtoMapper;

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
    public ResponseEntity<ResumenIvaComprasMensualResponse> resumenIvaComprasMensual(@PathVariable Integer anho, @PathVariable Integer mes) {
        return ResponseEntity.ok(resumenIvaComprasMensualDtoMapper.toResponse(service.getResumenIvaComprasMensual(anho, mes)));
    }

    @GetMapping("/resumen/iva/compras/posicion/{anho}/{mes}")
    public ResponseEntity<List<ResumenIvaComprasMensualPosicionResponse>> resumenIvaComprasPosicionMensual(@PathVariable Integer anho, @PathVariable Integer mes) {
        return ResponseEntity.ok(service.getAllResumenIvaComprasMensualPosicion(anho, mes).stream()
                .map(resumenIvaComprasMensualPosicionDtoMapper::toResponse)
                .toList());
    }

}
