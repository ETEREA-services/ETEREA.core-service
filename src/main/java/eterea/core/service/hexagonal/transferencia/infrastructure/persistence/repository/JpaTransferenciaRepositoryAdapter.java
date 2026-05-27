package eterea.core.service.hexagonal.transferencia.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.transferencia.domain.ports.out.TransferenciaRepository;
import eterea.core.service.hexagonal.transferencia.infrastructure.persistence.mapper.TransferenciaMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaTransferenciaRepositoryAdapter implements TransferenciaRepository {

    private final JpaTransferenciaRepository jpaRepository;
    private final TransferenciaMapper mapper;

    public JpaTransferenciaRepositoryAdapter(JpaTransferenciaRepository jpaRepository, TransferenciaMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Transferencia> findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(
            Integer negocioIdDesde, Integer negocioIdHasta, Long numeroTransferencia) {
        return jpaRepository.findByNegocioIdDesdeAndNegocioIdHastaAndNumeroTransferencia(
                negocioIdDesde, negocioIdHasta, numeroTransferencia
        ).map(mapper::toDomain);
    }
}
