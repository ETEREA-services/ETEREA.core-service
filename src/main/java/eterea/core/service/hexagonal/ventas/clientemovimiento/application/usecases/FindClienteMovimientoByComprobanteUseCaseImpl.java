package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.application.exception.ClienteMovimientoException;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindClienteMovimientoByComprobanteUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.stereotype.Component;

@Component
public class FindClienteMovimientoByComprobanteUseCaseImpl implements FindClienteMovimientoByComprobanteUseCase {

    private final ClienteMovimientoRepository repository;

    public FindClienteMovimientoByComprobanteUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ClienteMovimiento findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante) {
        return repository.findByComprobanteIdAndPuntoVentaAndNumeroComprobante(comprobanteId, puntoVenta, numeroComprobante)
                .orElseThrow(() -> new ClienteMovimientoException(comprobanteId, puntoVenta, numeroComprobante));
    }

    @Override
    public ClienteMovimiento findByFactura(String letraComprobante, Byte debita,
            Integer puntoVenta, Long numeroComprobante) {
        return repository.findByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteAndComprobanteDebita(
                letraComprobante, (byte) 0, puntoVenta, numeroComprobante, debita)
                .orElseThrow(() -> new ClienteMovimientoException(letraComprobante, debita, puntoVenta, numeroComprobante));
    }
}
