package eterea.core.service.hexagonal.legajo.infrastructure.persistence.repository.adapter;

import eterea.core.service.hexagonal.legajo.domain.model.Legajo;
import eterea.core.service.hexagonal.legajo.domain.ports.out.LegajoRepository;
import eterea.core.service.hexagonal.legajo.infrastructure.persistence.mapper.LegajoMapper;
import eterea.core.service.hexagonal.legajo.infrastructure.persistence.repository.JpaLegajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaLegajoRepositoryAdapter implements LegajoRepository {

    private final JpaLegajoRepository jpaLegajoRepository;
    private final LegajoMapper legajoMapper;

    @Override
    public List<Legajo> findAll() {
        return jpaLegajoRepository.findAll()
                .stream()
                .map(legajoMapper::toDomainModel)
                .toList();
    }

    @Override
    public Optional<Legajo> findByLegajoId(Integer legajoId) {
        return jpaLegajoRepository.findByLegajoId(legajoId)
                .map(legajoMapper::toDomainModel);
    }

    @Override
    public Legajo createLegajo(Legajo legajo) {
        return legajoMapper.toDomainModel(jpaLegajoRepository.save(legajoMapper.toLegajoEntity(legajo)));
    }

    @Override
    public List<Legajo> findAllContainingSubstrings(List<String> substrings) {
        return jpaLegajoRepository.findAllByStrings(substrings)
                .stream()
                .map(legajoMapper::toDomainModel)
                .toList();
    }

}
