/**
 * 
 */
package eterea.core.service.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.ClienteMovimiento;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	List<ClienteMovimiento> findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(
			OffsetDateTime fechaComprobante, Integer puntoVenta, Byte libroIva);

	List<ClienteMovimiento> findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(
			String letraComprobante, Byte recibo, Integer puntoVenta, Long numeroComprobanteDesde,
			Long numeroComprobanteHasta, Byte debita);

	Optional<ClienteMovimiento> findByClienteMovimientoId(Long clienteMovimientoId);

	Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteOrderByNumeroComprobanteDesc(Byte recibo,
			Integer puntoVenta, String letraComprobante);

	@Modifying
	@Query("delete from ClienteMovimiento where fechaComprobante = :fechaComprobante and comprobanteId = :comprobanteId and puntoVenta = :puntoVenta and numeroComprobante = :numeroComprobante")
	void deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(OffsetDateTime fechaComprobante,
			Integer comprobanteId,
			Integer puntoVenta,
			Long numeroComprobante);

	/*
	 * @author Sebastian
	 * Metodo existReserva extraido de VB6
	 */
	@Query("""
			    SELECT cm
			    FROM ClienteMovimiento cm
			    INNER JOIN cm.comprobante c
			    WHERE cm.reservaId = :reservaId
			    AND c.debita = 1
			    AND cm.anulada = 0
			""")
	Optional<ClienteMovimiento> findFirstByReservaId(@Param("reservaId") Long reservaId);

	@Query("""
			SELECT cm
			FROM ClienteMovimiento cm
			INNER JOIN cm.comprobante c
			WHERE cm.reservaId IN :reservaIds
			AND c.debita = 1
			AND cm.anulada = 0
			""")
	List<ClienteMovimiento> findAllByReservaIdsIn(@Param("reservaIds") List<Long> reservaIds);

	List<ClienteMovimiento> findByComprobanteIdAndFechaComprobanteBetween(Integer comprobanteId, OffsetDateTime desde, OffsetDateTime hasta);

	Slice<ClienteMovimiento> findSliceByComprobanteIdAndFechaComprobanteBetween(Integer comprobanteId,
			OffsetDateTime desde, OffsetDateTime hasta, Pageable pageable);

}
