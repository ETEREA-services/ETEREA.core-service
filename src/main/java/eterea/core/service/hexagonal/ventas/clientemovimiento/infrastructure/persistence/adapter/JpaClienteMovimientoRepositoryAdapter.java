package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.mapper.ClienteMovimientoMapper;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.persistence.repository.JpaClienteMovimientoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class JpaClienteMovimientoRepositoryAdapter implements ClienteMovimientoRepository {

    private final JpaClienteMovimientoRepository jpaRepository;
    private final ClienteMovimientoMapper mapper;

    public JpaClienteMovimientoRepositoryAdapter(JpaClienteMovimientoRepository jpaRepository,
                                                  ClienteMovimientoMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ClienteMovimiento> findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(
            Long clienteId, List<Integer> comprobanteIds) {
        return jpaRepository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ClienteMovimiento> findAllByReservaIdIn(List<Long> reservaIds) {
        return jpaRepository.findAllByReservaIdIn(reservaIds)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
        return jpaRepository.findAllByReservaId(reservaId)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ClienteMovimiento> findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(
            OffsetDateTime fechaComprobante, Integer puntoVenta, Byte libroIva) {
        return jpaRepository.findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(
                fechaComprobante, puntoVenta, libroIva)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ClienteMovimiento> findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(
            String letraComprobante, Byte recibo, Integer puntoVenta,
            Long numeroComprobanteDesde, Long numeroComprobanteHasta, Byte debita) {
        return jpaRepository.findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(
                letraComprobante, recibo, puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta, debita)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<ClienteMovimiento> findAllByFechaComprobanteBetweenAndComprobanteLibroIva(
            OffsetDateTime desde, OffsetDateTime hasta, Byte libroIva, Sort sort) {
        return jpaRepository.findAllByFechaComprobanteBetweenAndComprobanteLibroIva(desde, hasta, libroIva, sort)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteMovimiento> findByClienteMovimientoId(Long clienteMovimientoId) {
        return jpaRepository.findByClienteMovimientoId(clienteMovimientoId)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ClienteMovimiento> findByComprobanteIdAndPuntoVentaAndNumeroComprobante(
            Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        return jpaRepository.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ClienteMovimiento> findByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteAndComprobanteDebita(
            String letraComprobante, Byte recibo, Integer puntoVenta,
            Long numeroComprobante, Byte debita) {
        return jpaRepository.findByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteAndComprobanteDebita(
                letraComprobante, recibo, puntoVenta, numeroComprobante, debita)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteDebitaOrderByNumeroComprobanteDesc(
            Byte recibo, Integer puntoVenta, String letraComprobante, Byte debita) {
        return jpaRepository.findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteDebitaOrderByNumeroComprobanteDesc(
                recibo, puntoVenta, letraComprobante, debita)
                .map(mapper::toDomain);
    }

    @Override
    public Optional<ClienteMovimiento> findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteIdAndComprobanteDebitaOrderByNumeroComprobanteDesc(
            Byte recibo, Integer puntoVenta, String letraComprobante,
            Integer comprobanteId, Byte debita) {
        return jpaRepository.findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteIdAndComprobanteDebitaOrderByNumeroComprobanteDesc(
                recibo, puntoVenta, letraComprobante, comprobanteId, debita)
                .map(mapper::toDomain);
    }

    @Override
    public List<ClienteMovimiento> findAllByClienteMovimientoIdIn(List<Long> clienteMovimientoIds) {
        return jpaRepository.findAllByClienteMovimientoIdIn(clienteMovimientoIds)
                .stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public ClienteMovimiento save(ClienteMovimiento clienteMovimiento) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(clienteMovimiento)));
    }

    @Override
    public void deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(
            OffsetDateTime fechaComprobante, Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        jpaRepository.deleteAllByFechaComprobanteAndComprobanteIdAndPuntoVentaAndNumeroComprobante(
                fechaComprobante, comprobanteId, puntoVenta, numeroComprobante);
    }
}
