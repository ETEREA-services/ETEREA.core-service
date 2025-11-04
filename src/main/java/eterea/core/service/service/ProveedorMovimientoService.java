package eterea.core.service.service;

import eterea.core.service.kotlin.model.ProveedorMovimiento;
import eterea.core.service.kotlin.repository.ProveedorMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorMovimientoService {

    private final ProveedorMovimientoRepository repository;

    public List<ProveedorMovimiento> findAllByProveedorId(Long proveedorId) {
        return repository.findAllByProveedorIdOrderByFechaComprobante(proveedorId);
    }

    public List<ProveedorMovimiento> findAllByRegimenInformacionCompras(OffsetDateTime desde, OffsetDateTime hasta) {
        return repository
                .findAllByFechaContableBetweenAndComprobanteLibroIva(desde, hasta, (byte) 1, Sort.by("fechaComprobante").ascending().and(Sort.by("prefijo").ascending().and(Sort.by("numeroComprobante"))))
                .stream()
                .filter(movimiento -> movimiento.getMontoIva().add(movimiento.getMontoIva105().add(movimiento.getMontoIva27())).compareTo(BigDecimal.ZERO) != 0)
                .toList();
    }

}
