package eterea.core.api.rest.controller;

import eterea.core.api.rest.kotlin.model.Empresa;
import eterea.core.api.rest.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/empresa", "/empresa"})
public class EmpresaController {

    private final EmpresaService service;

    @Autowired
    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping("/top")
    public ResponseEntity<Empresa> findTop() {
        return ResponseEntity.ok(service.findTop());
    }

}
