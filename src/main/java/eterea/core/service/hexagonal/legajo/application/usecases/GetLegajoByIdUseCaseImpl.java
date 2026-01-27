package eterea.core.service.hexagonal.legajo.application.usecases;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.domain.ports.in.GetLegajoByIdUseCase;
import eterea.core.service.hexagonal.legajo.domain.ports.out.LegajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetLegajoByIdUseCaseImpl implements GetLegajoByIdUseCase {

    private final LegajoRepository legajoRepository;

    @Override
    public Optional<Legajo> findByLegajoId(Integer legajoId) {
        return legajoRepository.findByLegajoId(legajoId);
    }

}
