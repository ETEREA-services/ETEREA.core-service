package eterea.core.service.hexagonal.empresa.domain.ports.in;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;

import java.util.Optional;

public interface GetLastEmpresaUseCase {

    Optional<Empresa> findLast();

}
