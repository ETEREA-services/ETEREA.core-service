/**
 * 
 */
package eterea.core.service.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.ClienteMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteMovimientoRepository extends JpaRepository<ClienteMovimiento, Long> {

	List<ClienteMovimiento> findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(Long clienteId,
			List<Integer> comprobanteIds);

	List<ClienteMovimiento> findAllByReservaIdIn(List<Long> reservaIds);

	List<ClienteMovimiento> findAllByReservaId(Long reservaId);

	Optional<ClienteMovimiento> findByClienteMovimientoId(Long clienteMovimientoId);

	Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc(Integer recibo,
			Integer puntoVenta, String letraComprobante);

}
