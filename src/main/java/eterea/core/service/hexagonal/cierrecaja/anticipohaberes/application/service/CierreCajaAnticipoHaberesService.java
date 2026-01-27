package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.application.service;

import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.AnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.CierreCajaAnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.ports.in.SaveAllAnticipoHaberesUseCase;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.ports.out.CierreCajaAnticipoHaberesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CierreCajaAnticipoHaberesService implements SaveAllAnticipoHaberesUseCase {

    private final CierreCajaAnticipoHaberesRepository cierreCajaAnticipoHaberesRepository;

    @Override
    public List<CierreCajaAnticipoHaberes> saveAllAnticipoHaberes(Long cierreCajaId, Long userId, List<AnticipoHaberes> anticipos) {
        List<CierreCajaAnticipoHaberes> anticiposComplete = anticipos.stream()
                .map(anticipo -> CierreCajaAnticipoHaberes.builder()
                        .cierreCajaId(cierreCajaId)
                        .legajoId(anticipo.getLegajoId())
                        .fecha(anticipo.getFecha())
                        .importe(anticipo.getImporte())
                        .userId(userId)
                        .build())
                .toList();
        return cierreCajaAnticipoHaberesRepository.saveAllAnticipoHaberesByCierreCaja(anticiposComplete);
    }

}
