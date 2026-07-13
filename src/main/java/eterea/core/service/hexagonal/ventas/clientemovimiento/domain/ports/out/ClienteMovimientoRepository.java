package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import org.springframework.data.domain.Sort;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface ClienteMovimientoRepository {

    List<ClienteMovimiento> findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(
            Long clienteId, List<Integer> comprobanteIds);

    List<ClienteMovimiento> findAllByReservaIdIn(List<Long> reservaIds);

    List<ClienteMovimiento> findAllByReservaId(Long reservaId);

    List<ClienteMovimiento> findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(
            OffsetDateTime fechaComprobante, Integer puntoVenta, Byte libroIva);

    List<ClienteMovimiento> findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(
            String letraComprobante, Byte recibo, Integer puntoVenta,
            Long numeroComprobanteDesde, Long numeroComprobanteHasta, Byte debita);

    List<ClienteMovimiento> findAllByFechaComprobanteBetweenAndComprobanteLibroIva(
            OffsetDateTime desde, OffsetDateTime hasta, Byte libroIva, Sort sort);

    Optional<ClienteMovimiento> findByClienteMovimientoId(Long clienteMovimientoId);

    Optional<ClienteMovimiento> findByComprobanteIdAndPuntoVentaAndNumeroComprobante(
            Integer comprobanteId, Integer puntoVenta, Long numeroComprobante);

    Optional<ClienteMovimiento> findByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteAndComprobanteDebita(
            String letraComprobante, Byte recibo, Integer puntoVenta,
            Long numeroComprobante, Byte debita);

    Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteDebitaOrderByNumeroComprobanteDesc(
            Byte recibo, Integer puntoVenta, String letraComprobante, Byte debita);

    Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteIdAndComprobanteDebitaOrderByNumeroComprobanteDesc(
            Byte recibo, Integer puntoVenta, String letraComprobante,
            Integer comprobanteId, Byte debita);

    List<ClienteMovimiento> findAllByClienteMovimientoIdIn(List<Long> clienteMovimientoIds);

    ClienteMovimiento save(ClienteMovimiento clienteMovimiento);

    void deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(
            OffsetDateTime fechaComprobante, Integer comprobanteId, Integer puntoVenta, Long numeroComprobante);
}
