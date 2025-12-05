package eterea.core.service.hexagonal.empresa.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.empresa.infrastructure.persistence.entity.EmpresaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaJpaRepository extends JpaRepository<EmpresaEntity, Integer> {

    Optional<EmpresaEntity> findTopByOrderByEmpresaIdDesc();

}
