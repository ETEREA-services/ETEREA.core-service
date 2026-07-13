package eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.model.CierreCajaAnticipoHaberes;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.domain.ports.out.CierreCajaAnticipoHaberesRepository;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.entity.CierreCajaAnticipoHaberesEntity;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.mapper.CierreCajaAnticipoHaberesMapper;
import eterea.core.service.hexagonal.cierrecaja.anticipohaberes.infrastructure.persistence.repository.JpaCierreCajaAnticipoHaberesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class JpaCierreCajaAnticipoHaberesRepositoryAdapter implements CierreCajaAnticipoHaberesRepository {

    private final JpaCierreCajaAnticipoHaberesRepository jpaCierreCajaAnticipoHaberesRepository;
    private final CierreCajaAnticipoHaberesMapper cierreCajaAnticipoHaberesMapper;

    @Override
    public List<CierreCajaAnticipoHaberes> saveAllAnticipoHaberesByCierreCaja(List<CierreCajaAnticipoHaberes> anticipos) {
        List<CierreCajaAnticipoHaberesEntity> anticiposSaved = jpaCierreCajaAnticipoHaberesRepository.saveAll(anticipos.stream()
                .map(cierreCajaAnticipoHaberesMapper::toEntity)
                .toList());
        return anticiposSaved.stream()
                .map(cierreCajaAnticipoHaberesMapper::toDomain)
                .toList();
    }

}
