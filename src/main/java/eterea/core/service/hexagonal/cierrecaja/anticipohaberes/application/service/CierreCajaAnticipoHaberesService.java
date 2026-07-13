package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.application.service;

import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.AnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.CierreCajaAnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.ports.in.SaveAllAnticipoHaberesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CierreCajaAnticipoHaberesService {

    private final SaveAllAnticipoHaberesUseCase saveAllAnticipoHaberesUseCase;

    public List<CierreCajaAnticipoHaberes> saveAllAnticipoHaberes(Long cierreCajaId, Long userId, List<AnticipoHaberes> anticipos) {
        return saveAllAnticipoHaberesUseCase.saveAllAnticipoHaberes(cierreCajaId, userId, anticipos);
    }

}
