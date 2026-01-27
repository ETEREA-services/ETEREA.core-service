package eterea.core.service.hexagonal.legajo.application.usecases;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.domain.ports.in.CreateLegajoUseCase;
import eterea.core.service.hexagonal.legajo.domain.ports.out.LegajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateLegajoUseCaseImpl implements CreateLegajoUseCase {

    private final LegajoRepository legajoRepository;

    @Override
    public Legajo createLegajo(Legajo legajo) {
        return legajoRepository.createLegajo(legajo);
    }

}
