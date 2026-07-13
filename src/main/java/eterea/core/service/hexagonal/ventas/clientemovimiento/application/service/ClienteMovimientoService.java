package eterea.core.service.hexagonal.ventas.clientemovimiento.application.service;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
@Slf4j
public class ClienteMovimientoService {

    private final FindClienteMovimientosAsociablesUseCase findClienteMovimientosAsociablesUseCase;
    private final FindClienteMovimientosByReservaUseCase findClienteMovimientosByReservaUseCase;
    private final FindClienteMovimientosFacturadosUseCase findClienteMovimientosFacturadosUseCase;
    private final FindClienteMovimientosByIdsUseCase findClienteMovimientosByIdsUseCase;
    private final FindClienteMovimientoByIdUseCase findClienteMovimientoByIdUseCase;
    private final FindClienteMovimientoByComprobanteUseCase findClienteMovimientoByComprobanteUseCase;
    private final FindNextNumeroComprobanteUseCase findNextNumeroComprobanteUseCase;
    private final CreateClienteMovimientoUseCase createClienteMovimientoUseCase;
    private final UpdateClienteMovimientoUseCase updateClienteMovimientoUseCase;
    private final DeleteClienteMovimientoUseCase deleteClienteMovimientoUseCase;

    public ClienteMovimientoService(
            FindClienteMovimientosAsociablesUseCase findClienteMovimientosAsociablesUseCase,
            FindClienteMovimientosByReservaUseCase findClienteMovimientosByReservaUseCase,
            FindClienteMovimientosFacturadosUseCase findClienteMovimientosFacturadosUseCase,
            FindClienteMovimientosByIdsUseCase findClienteMovimientosByIdsUseCase,
            FindClienteMovimientoByIdUseCase findClienteMovimientoByIdUseCase,
            FindClienteMovimientoByComprobanteUseCase findClienteMovimientoByComprobanteUseCase,
            FindNextNumeroComprobanteUseCase findNextNumeroComprobanteUseCase,
            CreateClienteMovimientoUseCase createClienteMovimientoUseCase,
            UpdateClienteMovimientoUseCase updateClienteMovimientoUseCase,
            DeleteClienteMovimientoUseCase deleteClienteMovimientoUseCase) {
        this.findClienteMovimientosAsociablesUseCase = findClienteMovimientosAsociablesUseCase;
        this.findClienteMovimientosByReservaUseCase = findClienteMovimientosByReservaUseCase;
        this.findClienteMovimientosFacturadosUseCase = findClienteMovimientosFacturadosUseCase;
        this.findClienteMovimientosByIdsUseCase = findClienteMovimientosByIdsUseCase;
        this.findClienteMovimientoByIdUseCase = findClienteMovimientoByIdUseCase;
        this.findClienteMovimientoByComprobanteUseCase = findClienteMovimientoByComprobanteUseCase;
        this.findNextNumeroComprobanteUseCase = findNextNumeroComprobanteUseCase;
        this.createClienteMovimientoUseCase = createClienteMovimientoUseCase;
        this.updateClienteMovimientoUseCase = updateClienteMovimientoUseCase;
        this.deleteClienteMovimientoUseCase = deleteClienteMovimientoUseCase;
    }

    public List<ClienteMovimiento> findTop200Asociables(Long clienteId, Integer comprobanteId) {
        return findClienteMovimientosAsociablesUseCase.findTop200Asociables(clienteId, comprobanteId);
    }

    public List<ClienteMovimiento> findAllByReservaIds(List<Long> reservaIds) {
        return findClienteMovimientosByReservaUseCase.findAllByReservaIds(reservaIds);
    }

    public List<ClienteMovimiento> findAllByReservaId(Long reservaId) {
        return findClienteMovimientosByReservaUseCase.findAllByReservaId(reservaId);
    }

    public List<ClienteMovimiento> findAllFacturadosByFecha(OffsetDateTime fecha) {
        return findClienteMovimientosFacturadosUseCase.findAllFacturadosByFecha(fecha);
    }

    public List<ClienteMovimiento> findAllFacturasByRango(String letraComprobante, Byte debita,
            Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta) {
        return findClienteMovimientosFacturadosUseCase.findAllFacturasByRango(letraComprobante, debita,
                puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta);
    }

    public List<ClienteMovimiento> findAllByRegimenInformacionVentas(OffsetDateTime desde, OffsetDateTime hasta) {
        return findClienteMovimientosFacturadosUseCase.findAllByRegimenInformacionVentas(desde, hasta);
    }

    public List<ClienteMovimiento> findAllByIds(List<Long> clienteMovimientoIds) {
        return findClienteMovimientosByIdsUseCase.findAllByIds(clienteMovimientoIds);
    }

    public ClienteMovimiento findByClienteMovimientoId(Long clienteMovimientoId) {
        return findClienteMovimientoByIdUseCase.findByClienteMovimientoId(clienteMovimientoId);
    }

    public ClienteMovimiento findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        return findClienteMovimientoByComprobanteUseCase.findByComprobante(comprobanteId, puntoVenta, numeroComprobante);
    }

    public ClienteMovimiento findByFactura(String letraComprobante, Byte debita,
            Integer puntoVenta, Long numeroComprobante) {
        return findClienteMovimientoByComprobanteUseCase.findByFactura(letraComprobante, debita, puntoVenta, numeroComprobante);
    }

    public ClienteMovimiento add(ClienteMovimiento clienteMovimiento) {
        return createClienteMovimientoUseCase.add(clienteMovimiento);
    }

    public ClienteMovimiento update(ClienteMovimiento clienteMovimiento, Long clienteMovimientoId) {
        return updateClienteMovimientoUseCase.update(clienteMovimiento, clienteMovimientoId);
    }

    public Long nextNumeroFactura(String letraComprobante, Integer puntoVenta, Integer comprobanteId) {
        return findNextNumeroComprobanteUseCase.nextNumeroFactura(letraComprobante, puntoVenta, comprobanteId);
    }

    public Long nextNumeroNotaCredito(String letraComprobante, Integer puntoVenta, Integer comprobanteId) {
        return findNextNumeroComprobanteUseCase.nextNumeroNotaCredito(letraComprobante, puntoVenta, comprobanteId);
    }

    public void deleteAll0ByFecha(OffsetDateTime fecha) {
        deleteClienteMovimientoUseCase.deleteAll0ByFecha(fecha);
    }
}
