package eterea.core.service.hexagonal.legajo.application.usecases;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.domain.ports.in.GetAllLegajosUseCase;
import eterea.core.service.hexagonal.legajo.domain.ports.out.LegajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllLegajosUseCaseImpl implements GetAllLegajosUseCase {

    private final LegajoRepository legajoRepository;

    @Override
    public List<Legajo> getAllLegajos() {
        return legajoRepository.findAll();
    }

}
