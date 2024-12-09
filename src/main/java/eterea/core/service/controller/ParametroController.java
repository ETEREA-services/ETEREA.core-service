package eterea.core.service.controller;

import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.service.ParametroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/core/parametro", "/parametro"})
public class ParametroController {

    private final ParametroService service;

    public ParametroController(ParametroService service) {
        this.service = service;
    }

    @GetMapping("/top")
    public ResponseEntity<Parametro> findTop() {
        return new ResponseEntity<>(service.findTop(), HttpStatus.OK);
    }

}
