package eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.ports.out.ReservaArticuloRepository;
import eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.mapper.ReservaArticuloMapper;
import eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.repository.JpaReservaArticuloRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaReservaArticuloRepositoryAdapter implements ReservaArticuloRepository {

    private final JpaReservaArticuloRepository jpaRepository;
    private final ReservaArticuloMapper mapper;

    public JpaReservaArticuloRepositoryAdapter(JpaReservaArticuloRepository jpaRepository, ReservaArticuloMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ReservaArticulo> findAllByReservaId(Long reservaId) {
        return jpaRepository.findAllByReservaId(reservaId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservaArticulo> findAllByReservaIdAndVoucherId(Long reservaId, Long voucherId) {
        return jpaRepository.findAllByReservaIdAndVoucherId(reservaId, voucherId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReservaArticulo> findByReservaArticuloId(Long reservaArticuloId) {
        return jpaRepository.findByReservaArticuloId(reservaArticuloId)
                .map(mapper::toDomain);
    }

    @Override
    public void deleteByReservaArticuloId(Long reservaArticuloId) {
        jpaRepository.deleteByReservaArticuloId(reservaArticuloId);
    }

    @Override
    public List<ReservaArticulo> saveAll(List<ReservaArticulo> reservaArticulos) {
        return jpaRepository.saveAll(reservaArticulos.stream()
                .map(mapper::toEntity)
                .collect(Collectors.toList())).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaArticulo save(ReservaArticulo reservaArticulo) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(reservaArticulo)));
    }

}
