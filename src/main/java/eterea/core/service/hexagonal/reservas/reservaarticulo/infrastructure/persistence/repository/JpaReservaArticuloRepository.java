package eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.entity.ReservaArticuloEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaReservaArticuloRepository extends JpaRepository<ReservaArticuloEntity, Long> {

    List<ReservaArticuloEntity> findAllByReservaId(Long reservaId);
    List<ReservaArticuloEntity> findAllByReservaIdAndVoucherId(Long reservaId, Long voucherId);
    Optional<ReservaArticuloEntity> findByReservaArticuloId(Long reservaArticuloId);
    void deleteByReservaArticuloId(Long reservaArticuloId);

}