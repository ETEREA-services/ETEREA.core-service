package eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaTransferenciaRepository extends JpaRepository<TransferenciaEntity, Long> {

    Optional<TransferenciaEntity> findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(
            Integer negocioIdDesde,
            Integer negocioIdHasta,
            Long numeroTransferencia
    );

}
