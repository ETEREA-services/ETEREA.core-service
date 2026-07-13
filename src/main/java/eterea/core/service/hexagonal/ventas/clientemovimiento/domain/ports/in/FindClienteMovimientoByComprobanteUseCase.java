package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;

public interface FindClienteMovimientoByComprobanteUseCase {
    ClienteMovimiento findByComprobante(Integer comprobanteId, Integer puntoVenta, Long numeroComprobante);
    ClienteMovimiento findByFactura(String letraComprobante, Byte debita, Integer puntoVenta, Long numeroComprobante);
}
