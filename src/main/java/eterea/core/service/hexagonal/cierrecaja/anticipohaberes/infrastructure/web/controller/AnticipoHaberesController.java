package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.controller;

import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.application.service.CierreCajaAnticipoHaberesService;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.CierreCajaAnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.dto.AnticipoHaberesRequest;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.dto.AnticipoHaberesResponse;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.mapper.AnticipoHaberesDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/core/cierrecaja/anticipohaberes", "/cierrecaja/anticipohaberes"})
@RequiredArgsConstructor
public class AnticipoHaberesController {

    private final CierreCajaAnticipoHaberesService cierreCajaAnticipoHaberesService;
    private final AnticipoHaberesDtoMapper anticipoHaberesDtoMapper;

    @PostMapping("/register/{cierreCajaId}/user/{userId}")
    public ResponseEntity<List<AnticipoHaberesResponse>> registerAnticipoHaberes(@RequestBody List<AnticipoHaberesRequest> anticipos, @PathVariable Long cierreCajaId, @PathVariable Long userId) {
        List<CierreCajaAnticipoHaberes> anticiposSaved = cierreCajaAnticipoHaberesService.saveAllAnticipoHaberes(
                cierreCajaId,
                userId,
                anticipos.stream()
                        .map(anticipoHaberesDtoMapper::toData)
                        .toList());
        return ResponseEntity.ok(anticiposSaved.stream()
                .map(anticipoHaberesDtoMapper::toResponse)
                .toList());
    }

}
