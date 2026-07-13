package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.entity.CuentaMovimientoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface JpaCuentaMovimientoRepository extends JpaRepository<CuentaMovimientoEntity, Long> {

    List<CuentaMovimientoEntity> findAllByFechaAndOrden(OffsetDateTime fechaContable, Integer ordenContable);

    List<CuentaMovimientoEntity> findAllByFechaBetween(OffsetDateTime fechaDesde, OffsetDateTime fechaHasta);

    Optional<CuentaMovimientoEntity> findByCuentaMovimientoId(Long cuentaMovimientoId);

    Optional<CuentaMovimientoEntity> findFirstByFechaAndOrdenOrderByItemDesc(OffsetDateTime fecha, Integer orden);

    Optional<CuentaMovimientoEntity> findFirstByFechaOrderByOrdenDesc(OffsetDateTime fecha);

    @Query("SELECT COALESCE(SUM(cm.importe), 0) FROM CuentaMovimientoEntity cm WHERE cm.fecha BETWEEN :desde AND :hasta AND cm.numeroCuenta = :numeroCuenta AND cm.debita = :debita")
    BigDecimal calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, Integer debita, OffsetDateTime desde, OffsetDateTime hasta);

    @Query("SELECT COALESCE(SUM(cm.importe), 0) FROM CuentaMovimientoEntity cm WHERE cm.fecha BETWEEN :desde AND :hasta AND cm.numeroCuenta = :numeroCuenta AND cm.debita = :debita AND cm.inflacion = :inflacion")
    BigDecimal calculateTotalByNumeroCuentaAndDebitaAndInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Integer inflacion, OffsetDateTime desde, OffsetDateTime hasta);

    @Modifying
    @Transactional
    @Query("DELETE FROM CuentaMovimientoEntity cm WHERE cm.fecha = :fechaContable AND cm.orden = :ordenContable")
    void deleteAllByFechaAndOrden(OffsetDateTime fechaContable, Integer ordenContable);

    List<CuentaMovimientoEntity> findAllByCierreCajaIdAndFechaBetween(Long cierreCajaId, OffsetDateTime desde, OffsetDateTime hasta);
}
