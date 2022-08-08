/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ArticuloMovimiento;

/**
 * @author daniel
 *
 */
@Repository
public interface IArticuloMovimientoRepository extends JpaRepository<ArticuloMovimiento, Long> {

	public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId);

}
