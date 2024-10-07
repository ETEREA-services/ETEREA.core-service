package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Empresa;
import eterea.core.service.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/empresa", "/empresa"})
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping("/top")
    public ResponseEntity<Empresa> findTop() {
        return ResponseEntity.ok(service.findTop());
    }

}
