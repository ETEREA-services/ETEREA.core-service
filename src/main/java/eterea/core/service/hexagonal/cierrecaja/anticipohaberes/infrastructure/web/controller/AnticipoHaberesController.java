package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.web.controller;

import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.CierreCajaAnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.ports.in.SaveAllAnticipoHaberesUseCase;
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

    private final SaveAllAnticipoHaberesUseCase saveAllAnticipoHaberesUseCase;
    private final AnticipoHaberesDtoMapper anticipoHaberesDtoMapper;

    @PostMapping("/register/{cierreCajaId}/user/{userId}")
    public ResponseEntity<List<AnticipoHaberesResponse>> registerAnticipoHaberes(@RequestBody List<AnticipoHaberesRequest> anticipos, @PathVariable Long cierreCajaId, @PathVariable Long userId) {
        List<CierreCajaAnticipoHaberes> anticiposSaved = saveAllAnticipoHaberesUseCase.saveAllAnticipoHaberes(
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
