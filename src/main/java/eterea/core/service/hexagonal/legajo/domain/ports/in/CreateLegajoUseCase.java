package eterea.core.service.hexagonal.legajo.domain.ports.in;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;

public interface CreateLegajoUseCase {

    Legajo createLegajo(Legajo legajo);

}
