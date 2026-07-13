package eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;

import java.time.OffsetDateTime;
import java.util.List;

public interface FindClienteMovimientosFacturadosUseCase {
    List<ClienteMovimiento> findAllFacturadosByFecha(OffsetDateTime fecha);
    List<ClienteMovimiento> findAllFacturasByRango(String letraComprobante, Byte debita,
            Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta);
    List<ClienteMovimiento> findAllByRegimenInformacionVentas(OffsetDateTime desde, OffsetDateTime hasta);
}
