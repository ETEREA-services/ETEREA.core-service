/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.CuentaMovimiento;

/**
 * @author daniel
 *
 */
@Repository
public interface ICuentaMovimientoRepository extends JpaRepository<CuentaMovimiento, Long> {

	public Optional<CuentaMovimiento> findByCuentaMovimientoId(Long cuentaMovimientoId);

}
