/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ClienteMovimiento;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteMovimientoRepository extends JpaRepository<ClienteMovimiento, Long> {

	public List<ClienteMovimiento> findAllByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(Long clienteId,
			List<Integer> comprobanteIds);

	public Optional<ClienteMovimiento> findByClienteMovimientoId(Long clienteMovimientoId);

}
