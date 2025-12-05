package eterea.core.service.hexagonal.empresa.domain.ports.out;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;

import java.util.Optional;

public interface EmpresaRepository {

    Optional<Empresa> findLast();

    void save(Empresa empresa);

}
