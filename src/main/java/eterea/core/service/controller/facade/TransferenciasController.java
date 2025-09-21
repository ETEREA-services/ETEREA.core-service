package eterea.core.service.controller.facade;

import eterea.core.service.kotlin.model.dto.TransferenciaWrapperDto;
import eterea.core.service.service.facade.TransferenciasService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/core/transferencias", "/transferencias"})
@RequiredArgsConstructor
public class TransferenciasController {

    private final TransferenciasService service;

    @GetMapping("/unique/{negocioIdDesde}/{negocioIdHasta}/{numeroTransferencia}")
    public ResponseEntity<TransferenciaWrapperDto> findByUnique(@PathVariable Integer negocioIdDesde, @PathVariable Integer negocioIdHasta, @PathVariable Long numeroTransferencia) {
        return ResponseEntity.ok(service.findByUnique(negocioIdDesde, negocioIdHasta, numeroTransferencia));
    }

    @PostMapping("/regenerate")
    public ResponseEntity<String> regenerate(@RequestBody TransferenciaWrapperDto transferenciaWrapper) {
        return ResponseEntity.ok(service.regenerate(transferenciaWrapper));
    }

}
