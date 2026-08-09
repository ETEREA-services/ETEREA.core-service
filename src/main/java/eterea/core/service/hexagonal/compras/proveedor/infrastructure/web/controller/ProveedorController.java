package eterea.core.service.hexagonal.compras.proveedor.infrastructure.web.controller;

import eterea.core.service.hexagonal.compras.proveedor.application.exception.ProveedorException;
import eterea.core.service.hexagonal.compras.proveedor.application.service.ProveedorService;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.web.dto.ProveedorResponse;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.web.mapper.ProveedorDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/proveedor", "/proveedor"})
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService service;
    private final ProveedorDtoMapper proveedorDtoMapper;

    @GetMapping("/{proveedorId}")
    public ResponseEntity<ProveedorResponse> findByProveedorId(@PathVariable Integer proveedorId) {
        try {
            return new ResponseEntity<>(proveedorDtoMapper.toResponse(service.findByProveedorId(proveedorId)),
                    HttpStatus.OK);
        } catch (ProveedorException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
