/**
 * 
 */
package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.entity.ClienteMovimientoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface JpaClienteMovimientoRepository extends JpaRepository<ClienteMovimientoEntity, Long> {

	List<ClienteMovimientoEntity> findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(Long clienteId,
	                                                                                                    List<Integer> comprobanteIds);

	List<ClienteMovimientoEntity> findAllByReservaIdIn(List<Long> reservaIds);

	List<ClienteMovimientoEntity> findAllByReservaId(Long reservaId);

	List<ClienteMovimientoEntity> findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(OffsetDateTime fechaComprobante, Integer puntoVenta, Byte libroIva);

	List<ClienteMovimientoEntity> findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(String letraComprobante, Byte recibo, Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta, Byte debita);

    List<ClienteMovimientoEntity> findAllByFechaComprobanteBetweenAndComprobanteLibroIva(OffsetDateTime desde, OffsetDateTime hasta, Byte libroIva, Sort sort);

	Optional<ClienteMovimientoEntity> findByClienteMovimientoId(Long clienteMovimientoId);

	Optional<ClienteMovimientoEntity> findByComprobanteIdAndPuntoVentaAndNumeroComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante);

	Optional<ClienteMovimientoEntity> findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteDebitaOrderByNumeroComprobanteDesc(
            Byte recibo,
			Integer puntoVenta,
            String letraComprobante,
            Byte debita
    );

    Optional<ClienteMovimientoEntity> findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteIdAndComprobanteDebitaOrderByNumeroComprobanteDesc(
            Byte recibo,
            Integer puntoVenta,
            String letraComprobante,
            Integer comprobanteId,
            Byte debita
    );

	Optional<ClienteMovimientoEntity> findByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteAndComprobanteDebita(
			String letraComprobante,
			Byte recibo,
			Integer puntoVenta,
			Long numeroComprobante,
			Byte debita
	);

	List<ClienteMovimientoEntity> findAllByClienteMovimientoIdIn(List<Long> clienteMovimientoIds);

	@Modifying
	@Query("delete from ClienteMovimientoEntity where fechaComprobante = :fechaComprobante and comprobanteId = :comprobanteId and puntoVenta = :puntoVenta and numeroComprobante = :numeroComprobante")
	void deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(OffsetDateTime fechaComprobante,
																					  Integer comprobanteId,
																					  Integer puntoVenta,
																					  Long numeroComprobante);

	List<ClienteMovimientoEntity> findAllByCierreCajaIdAndFechaComprobanteBetween(Long cierreCajaId, OffsetDateTime desde, OffsetDateTime hasta);

}
