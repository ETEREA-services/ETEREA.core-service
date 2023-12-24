/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.api.rest.kotlin.model.ClienteMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteMovimientoRepository extends JpaRepository<ClienteMovimiento, Long> {

	public List<ClienteMovimiento> findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(Long clienteId,
			List<Integer> comprobanteIds);

	public List<ClienteMovimiento> findAllByReservaIdIn(List<Long> reservaIds);

	public List<ClienteMovimiento> findAllByReservaId(Long reservaId);

	public Optional<ClienteMovimiento> findByClienteMovimientoId(Long clienteMovimientoId);

	public Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc(Integer recibo,
			Integer puntoVenta, String letraComprobante);

}
