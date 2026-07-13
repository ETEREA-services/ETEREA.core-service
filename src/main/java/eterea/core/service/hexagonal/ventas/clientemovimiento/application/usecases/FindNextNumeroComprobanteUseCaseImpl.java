package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindNextNumeroComprobanteUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.stereotype.Component;

@Component
public class FindNextNumeroComprobanteUseCaseImpl implements FindNextNumeroComprobanteUseCase {

    private final ClienteMovimientoRepository repository;

    public FindNextNumeroComprobanteUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Long nextNumeroFactura(String letraComprobante, Integer puntoVenta, Integer comprobanteId) {
        if (comprobanteId == null || comprobanteId == 0) {
            return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteDebitaOrderByNumeroComprobanteDesc(
                    (byte) 0, puntoVenta, letraComprobante, (byte) 1)
                .map(clienteMovimiento -> 1 + clienteMovimiento.getNumeroComprobante()).orElse(1L);
        }
        return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteIdAndComprobanteDebitaOrderByNumeroComprobanteDesc(
                (byte) 0, puntoVenta, letraComprobante, comprobanteId, (byte) 1)
            .map(clienteMovimiento -> 1 + clienteMovimiento.getNumeroComprobante()).orElse(1L);
    }

    @Override
    public Long nextNumeroNotaCredito(String letraComprobante, Integer puntoVenta, Integer comprobanteId) {
        if (comprobanteId == null || comprobanteId == 0) {
            return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteDebitaOrderByNumeroComprobanteDesc(
                    (byte) 0, puntoVenta, letraComprobante, (byte) 0)
                .map(clienteMovimiento -> 1 + clienteMovimiento.getNumeroComprobante()).orElse(1L);
        }
        return repository.findTopByReciboAndPuntoVentaAndLetraComprobanteAndComprobanteIdAndComprobanteDebitaOrderByNumeroComprobanteDesc(
                (byte) 0, puntoVenta, letraComprobante, comprobanteId, (byte) 0)
            .map(clienteMovimiento -> 1 + clienteMovimiento.getNumeroComprobante()).orElse(1L);
    }
}
