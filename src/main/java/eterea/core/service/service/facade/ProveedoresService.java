package eterea.core.service.service.facade;

import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import eterea.core.service.service.FacturaRendicionDetalleService;
import eterea.core.service.hexagonal.proveedormovimiento.application.service.ProveedorMovimientoService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProveedoresService {

    private final ProveedorMovimientoService proveedorMovimientoService;
    private final FacturaRendicionDetalleService facturaRendicionDetalleService;

    public ProveedoresService(ProveedorMovimientoService proveedorMovimientoService, FacturaRendicionDetalleService facturaRendicionDetalleService) {
        this.proveedorMovimientoService = proveedorMovimientoService;
        this.facturaRendicionDetalleService = facturaRendicionDetalleService;
    }

    public List<ProveedorMovimientoEntity> findAllByProveedorIdSinRendicion(Long proveedorId) {
        Map<String, ProveedorMovimientoEntity> proveedorMovimientos = new HashMap<>();

        proveedorMovimientoService.findAllByProveedorId(proveedorId).forEach(movimiento ->
                proveedorMovimientos.put(movimiento.comprobanteKey(), movimiento)
        );

        // Removemos los movimientos que tienen rendición
        facturaRendicionDetalleService.findAllByProveedorId(proveedorId).forEach(detalle ->
                proveedorMovimientos.remove(detalle.comprobanteKey())
        );

        // Convertimos a lista y ordenamos por fechaComprobante
        return proveedorMovimientos.values()
                .stream()
                .sorted(Comparator.comparing(ProveedorMovimientoEntity::getFechaComprobante, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toList());
    }

}
