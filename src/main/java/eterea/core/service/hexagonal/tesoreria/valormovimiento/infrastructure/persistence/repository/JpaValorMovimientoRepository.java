package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.entity.ValorMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaValorMovimientoRepository extends JpaRepository<ValorMovimientoEntity, Integer> {

    Optional<ValorMovimientoEntity> findByValorMovimientoId(Long valorMovimientoId);

    @Query("""
            SELECT vm
            FROM ValorMovimientoEntity vm
            WHERE vm.fechaContable >= :desde AND vm.fechaContable <= :hasta
                AND (CASE WHEN :cierreCajaOnly = TRUE
                   THEN (vm.cierreCajaId != 0 AND vm.cierreCajaId IS NOT NULL)
                   ELSE TRUE
                   END)
                AND (CASE WHEN :ingresosOnly = TRUE
                   THEN (vm.importe > 0)
                   ELSE TRUE
                   END)
            """)
    List<ValorMovimientoEntity> findAllByFechaContableBetween(
            OffsetDateTime desde,
            OffsetDateTime hasta,
            Boolean cierreCajaOnly,
            Boolean ingresosOnly
    );

    List<ValorMovimientoEntity> findAllByFechaContableAndOrdenContable(
            OffsetDateTime fechaContable,
            Integer ordenContable
    );

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("""
            DELETE FROM ValorMovimientoEntity vm
            WHERE vm.fechaContable = :fechaContable AND vm.ordenContable = :ordenContable
            """)
    void deleteAllByFechaContableAndOrdenContable(OffsetDateTime fechaContable, Integer ordenContable);

    List<ValorMovimientoEntity> findAllByCierreCajaIdAndFechaContableBetween(Long cierreCajaId, OffsetDateTime desde, OffsetDateTime hasta);

}
