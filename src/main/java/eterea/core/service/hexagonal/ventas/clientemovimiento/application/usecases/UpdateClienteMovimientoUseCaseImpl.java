package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.application.exception.ClienteMovimientoException;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.UpdateClienteMovimientoUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateClienteMovimientoUseCaseImpl implements UpdateClienteMovimientoUseCase {

    private final ClienteMovimientoRepository repository;

    public UpdateClienteMovimientoUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteMovimiento update(ClienteMovimiento newClienteMovimiento, Long clienteMovimientoId) {
        ClienteMovimiento clienteMovimiento = repository.findByClienteMovimientoId(clienteMovimientoId)
                .orElseThrow(() -> new ClienteMovimientoException(clienteMovimientoId));

        clienteMovimiento.setComprobanteId(newClienteMovimiento.getComprobanteId());
        clienteMovimiento.setPuntoVenta(newClienteMovimiento.getPuntoVenta());
        clienteMovimiento.setNumeroComprobante(newClienteMovimiento.getNumeroComprobante());
        clienteMovimiento.setFechaComprobante(newClienteMovimiento.getFechaComprobante());
        clienteMovimiento.setClienteId(newClienteMovimiento.getClienteId());
        clienteMovimiento.setFechaVencimiento(newClienteMovimiento.getFechaVencimiento());
        clienteMovimiento.setNegocioId(newClienteMovimiento.getNegocioId());
        clienteMovimiento.setEmpresaId(newClienteMovimiento.getEmpresaId());
        clienteMovimiento.setImporte(newClienteMovimiento.getImporte());
        clienteMovimiento.setCancelado(newClienteMovimiento.getCancelado());
        clienteMovimiento.setNeto(newClienteMovimiento.getNeto());
        clienteMovimiento.setNetoCancelado(newClienteMovimiento.getNetoCancelado());
        clienteMovimiento.setMontoIva(newClienteMovimiento.getMontoIva());
        clienteMovimiento.setMontoIvaRni(newClienteMovimiento.getMontoIvaRni());
        clienteMovimiento.setReintegroTurista(newClienteMovimiento.getReintegroTurista());
        clienteMovimiento.setFechaContable(newClienteMovimiento.getFechaContable());
        clienteMovimiento.setOrdenContable(newClienteMovimiento.getOrdenContable());
        clienteMovimiento.setRecibo(newClienteMovimiento.getRecibo());
        clienteMovimiento.setAsignado(newClienteMovimiento.getAsignado());
        clienteMovimiento.setAnulada(newClienteMovimiento.getAnulada());
        clienteMovimiento.setDecreto104316(newClienteMovimiento.getDecreto104316());
        clienteMovimiento.setLetraComprobante(newClienteMovimiento.getLetraComprobante());
        clienteMovimiento.setMontoExento(newClienteMovimiento.getMontoExento());
        clienteMovimiento.setReservaId(newClienteMovimiento.getReservaId());
        clienteMovimiento.setMontoCuentaCorriente(newClienteMovimiento.getMontoCuentaCorriente());
        clienteMovimiento.setCierreCajaId(newClienteMovimiento.getCierreCajaId());
        clienteMovimiento.setCierreRestaurantId(newClienteMovimiento.getCierreRestaurantId());
        clienteMovimiento.setNivel(newClienteMovimiento.getNivel());
        clienteMovimiento.setEliminar(newClienteMovimiento.getEliminar());
        clienteMovimiento.setCuentaCorriente(newClienteMovimiento.getCuentaCorriente());
        clienteMovimiento.setLetras(newClienteMovimiento.getLetras());
        clienteMovimiento.setCae(newClienteMovimiento.getCae());
        clienteMovimiento.setCaeVencimiento(newClienteMovimiento.getCaeVencimiento());
        clienteMovimiento.setCodigoBarras(newClienteMovimiento.getCodigoBarras());
        clienteMovimiento.setParticipacion(newClienteMovimiento.getParticipacion());
        clienteMovimiento.setMonedaId(newClienteMovimiento.getMonedaId());
        clienteMovimiento.setCotizacion(newClienteMovimiento.getCotizacion());
        clienteMovimiento.setObservaciones(newClienteMovimiento.getObservaciones());
        clienteMovimiento.setClienteMovimientoIdSlave(newClienteMovimiento.getClienteMovimientoIdSlave());
        clienteMovimiento.setTrackUuid(newClienteMovimiento.getTrackUuid());

        return repository.save(clienteMovimiento);
    }
}
