/**
 * 
 */
package eterea.core.api.rest.repository;

import java.time.OffsetDateTime;
import java.util.Optional;

import eterea.core.api.rest.kotlin.model.CuentaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ICuentaMovimientoRepository extends JpaRepository<CuentaMovimiento, Long>, ICuentaMovimientoRepositoryCustom {

	public Optional<CuentaMovimiento> findByCuentaMovimientoId(Long cuentaMovimientoId);

    public Optional<CuentaMovimiento> findFirstByFechaAndOrdenOrderByItemDesc(OffsetDateTime fecha, Integer orden);

}
