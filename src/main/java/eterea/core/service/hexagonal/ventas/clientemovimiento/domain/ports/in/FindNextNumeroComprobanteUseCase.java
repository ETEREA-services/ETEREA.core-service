package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

public interface FindNextNumeroComprobanteUseCase {
    Long nextNumeroFactura(String letraComprobante, Integer puntoVenta, Integer comprobanteId);
    Long nextNumeroNotaCredito(String letraComprobante, Integer puntoVenta, Integer comprobanteId);
}
