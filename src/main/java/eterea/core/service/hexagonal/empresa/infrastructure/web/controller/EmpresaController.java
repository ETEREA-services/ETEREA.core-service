package eterea.core.service.hexagonal.empresa.infrastructure.web.controller;

import eterea.core.service.hexagonal.empresa.domain.ports.in.GetLastEmpresaUseCase;
import eterea.core.service.hexagonal.empresa.infrastructure.web.dto.EmpresaResponse;
import eterea.core.service.hexagonal.empresa.infrastructure.web.mapper.EmpresaDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/empresa", "/empresa"})
@RequiredArgsConstructor
public class EmpresaController {

    private final GetLastEmpresaUseCase getLastEmpresaUseCase;
    private final EmpresaDtoMapper empresaDtoMapper;

    @GetMapping("/top")
    public ResponseEntity<EmpresaResponse> findTop() {
        return getLastEmpresaUseCase
                .findLast()
                .map(empresa -> ResponseEntity.ok(empresaDtoMapper.toResponse(empresa)))
                .orElse(ResponseEntity.notFound().build());
    }

}
