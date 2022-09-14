/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Reserva;

/**
 * @author daniel
 *
 */
@Repository
public interface IReservaRepository extends JpaRepository<Reserva, Long> {

	public Optional<Reserva> findByReservaId(Long reservaId);

	public List<Reserva> findTop100ByVerificadaAndFacturadaAndEliminadaAndPagaCacheutaAndFacturadoFueraAndAnuladaAndClienteIdGreaterThan(
			Byte verificada, Byte facturada, Byte eliminada, Byte pagaCacheuta, Byte facturadoFuera, Byte anulada,
			Long clienteId, Sort sort);

}
