package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindClienteMovimientosFacturadosUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Component
public class FindClienteMovimientosFacturadosUseCaseImpl implements FindClienteMovimientosFacturadosUseCase {

    private final ClienteMovimientoRepository repository;

    public FindClienteMovimientosFacturadosUseCaseImpl(ClienteMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ClienteMovimiento> findAllFacturadosByFecha(OffsetDateTime fecha) {
        return repository.findAllByFechaComprobanteAndPuntoVentaGreaterThanAndComprobanteLibroIva(fecha, 0, (byte) 1);
    }

    @Override
    public List<ClienteMovimiento> findAllFacturasByRango(String letraComprobante, Byte debita,
            Integer puntoVenta, Long numeroComprobanteDesde, Long numeroComprobanteHasta) {
        return repository.findAllByLetraComprobanteAndReciboAndPuntoVentaAndNumeroComprobanteBetweenAndComprobanteDebita(
            letraComprobante, (byte) 0, puntoVenta, numeroComprobanteDesde, numeroComprobanteHasta, debita);
    }

    @Override
    public List<ClienteMovimiento> findAllByRegimenInformacionVentas(OffsetDateTime desde, OffsetDateTime hasta) {
        return repository
                .findAllByFechaComprobanteBetweenAndComprobanteLibroIva(desde, hasta, (byte) 1,
                    Sort.by("puntoVenta").ascending().and(Sort.by("numeroComprobante")))
                .stream()
                .filter(movimiento -> movimiento.getMontoIva().add(movimiento.getMontoIvaRni()).compareTo(BigDecimal.ZERO) != 0)
                .toList();
    }
}
