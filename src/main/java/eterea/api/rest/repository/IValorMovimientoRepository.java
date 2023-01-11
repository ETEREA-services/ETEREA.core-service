/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ValorMovimiento;

/**
 * @author daniel
 *
 */
@Repository
public interface IValorMovimientoRepository extends JpaRepository<ValorMovimiento, Long> {

	public List<ValorMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId);

}
