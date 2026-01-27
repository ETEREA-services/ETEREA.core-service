package eterea.core.service.hexagonal.legajo.domain.ports.in;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;

import java.util.Optional;

public interface GetLegajoByIdUseCase {

    Optional<Legajo> findByLegajoId(Integer legajoId);

}
