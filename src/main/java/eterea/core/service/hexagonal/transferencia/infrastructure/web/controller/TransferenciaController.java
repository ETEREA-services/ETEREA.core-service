package eterea.core.service.hexagonal.transferencia.infrastructure.web.controller;

import eterea.core.service.kotlin.exception.TransferenciaException;
import eterea.core.service.hexagonal.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.transferencia.application.service.TransferenciaService;
import eterea.core.service.hexagonal.transferencia.infrastructure.web.dto.TransferenciaResponse;
import eterea.core.service.hexagonal.transferencia.infrastructure.web.mapper.TransferenciaDtoMapper;
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
    private final TransferenciaDtoMapper mapper;

    @GetMapping("/unique/{negocioIdDesde}/{negocioIdHasta}/{numeroTransferencia}")
    public ResponseEntity<TransferenciaResponse> findByUnique(@PathVariable Integer negocioIdDesde, @PathVariable Integer negocioIdHasta, @PathVariable Long numeroTransferencia) {
        try {
            Transferencia domain = service.findByUnique(negocioIdDesde, negocioIdHasta, numeroTransferencia);
            return ResponseEntity.ok(mapper.toResponse(domain));
        } catch (TransferenciaException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
