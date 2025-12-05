package eterea.core.service.hexagonal.empresa.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.domain.ports.out.EmpresaRepository;
import eterea.core.service.hexagonal.empresa.infrastructure.persistence.mapper.EmpresaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmpresaJpaRepositoryAdapter implements EmpresaRepository {

    private final EmpresaJpaRepository empresaJpaRepository;
    private final EmpresaMapper empresaMapper;

    @Override
    public Optional<Empresa> findLast() {
        return empresaJpaRepository.findTopByOrderByEmpresaIdDesc()
                .map(empresaMapper::toDomainModel);
    }

    @Override
    public void save(Empresa empresa) {
        empresaJpaRepository.save(empresaMapper.toEntity(empresa));
    }

}
