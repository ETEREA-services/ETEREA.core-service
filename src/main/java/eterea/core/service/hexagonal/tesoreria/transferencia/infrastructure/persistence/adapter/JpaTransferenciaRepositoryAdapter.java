package eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.tesoreria.transferencia.domain.model.Transferencia;
import eterea.core.service.hexagonal.tesoreria.transferencia.domain.ports.out.TransferenciaRepository;
import eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.mapper.TransferenciaMapper;
import eterea.core.service.hexagonal.tesoreria.transferencia.infrastructure.persistence.repository.JpaTransferenciaRepository;
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
