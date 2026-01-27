package eterea.core.service.hexagonal.legajo.application.service;

import eterea.core.service.hexagonal.legajo.domain.ports.in.CreateLegajoUseCase;
import eterea.core.service.hexagonal.legajo.domain.ports.in.GetAllLegajosContainingSubstringsUseCase;
import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.domain.ports.in.GetAllLegajosUseCase;
import eterea.core.service.hexagonal.legajo.domain.ports.in.GetLegajoByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LegajoService {

    private final CreateLegajoUseCase createLegajoUseCase;
    private final GetAllLegajosUseCase getAllLegajosUseCase;
    private final GetLegajoByIdUseCase getLegajoByIdUseCase;
    private final GetAllLegajosContainingSubstringsUseCase getAllLegajosContainingSubstringsUseCase;

    public Legajo createLegajo(Legajo legajo) {
        return createLegajoUseCase.createLegajo(legajo);
    }

    public List<Legajo> getAllLegajos() {
        return getAllLegajosUseCase.getAllLegajos();
    }

    public Optional<Legajo> findByLegajoId(Integer legajoId) {
        return getLegajoByIdUseCase.findByLegajoId(legajoId);
    }

    public List<Legajo> findAllContainingSubstrings(List<String> substrings) {
        return getAllLegajosContainingSubstringsUseCase.findAllContainingSubstrings(substrings);
    }

}
