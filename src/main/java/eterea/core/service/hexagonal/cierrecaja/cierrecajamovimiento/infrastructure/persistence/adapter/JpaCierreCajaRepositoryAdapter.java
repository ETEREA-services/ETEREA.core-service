package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.out.CierreCajaRepository;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.mapper.CierreCajaMapper;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.infrastructure.persistence.repository.JpaCierreCajaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaCierreCajaRepositoryAdapter implements CierreCajaRepository {

    private final JpaCierreCajaRepository jpaRepository;
    private final CierreCajaMapper mapper;

    @Override
    public Optional<CierreCaja> findByCierreCajaId(Long cierreCajaId) {
        return jpaRepository.findByCierreCajaId(cierreCajaId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<CierreCaja> findTopByFechaTope() {
        return jpaRepository.findTopByOrderByFechaTopeDesc()
                .map(mapper::toDomain);
    }

}
