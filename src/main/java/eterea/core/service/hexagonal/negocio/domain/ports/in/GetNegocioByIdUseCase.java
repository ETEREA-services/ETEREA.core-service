package eterea.core.service.hexagonal.negocio.domain.ports.in;

import eterea.core.service.hexagonal.negocio.domain.model.Negocio;

import java.util.Optional;

public interface GetNegocioByIdUseCase {

    Optional<Negocio> getNegocioById(Integer negocioId);

}
