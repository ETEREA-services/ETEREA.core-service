package eterea.core.service.hexagonal.empresa.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.domain.ports.out.EmpresaRepository;
import eterea.core.service.hexagonal.empresa.infrastructure.persistence.mapper.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaEmpresaRepositoryAdapter implements EmpresaRepository {

    private final JpaEmpresaRepository jpaEmpresaRepository;
    private final EmpresaMapper empresaMapper;

    @Override
    public Optional<Empresa> findLast() {
        return jpaEmpresaRepository.findTopByOrderByEmpresaIdDesc()
                .map(empresaMapper::toDomainModel);
    }

    @Override
    public void save(Empresa empresa) {
        jpaEmpresaRepository.save(empresaMapper.toEntity(empresa));
    }

}