package eterea.core.service.controller;

import eterea.core.service.kotlin.exception.TransferenciaException;
import eterea.core.service.kotlin.model.Transferencia;
import eterea.core.service.service.TransferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping({"/api/core/transferencia", "/transferencia"})
@RequiredArgsConstructor
public class TransferenciaController {

    private final TransferenciaService service;

    @GetMapping("/unique/{negocioIdDesde}/{negocioIdHasta}/{numeroTransferencia}")
    public ResponseEntity<Transferencia> findByUnique(@PathVariable Integer negocioIdDesde, @PathVariable Integer negocioIdHasta, @PathVariable Long numeroTransferencia) {
        try {
            return ResponseEntity.ok(service.findByUnique(negocioIdDesde, negocioIdHasta, numeroTransferencia));
        } catch (TransferenciaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
