package eterea.core.service.service.facade;

import eterea.core.service.kotlin.model.ProveedorMovimiento;
import eterea.core.service.service.FacturaRendicionDetalleService;
import eterea.core.service.service.ProveedorMovimientoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProveedoresService {

    private final ProveedorMovimientoService proveedorMovimientoService;
    private final FacturaRendicionDetalleService facturaRendicionDetalleService;

    public ProveedoresService(ProveedorMovimientoService proveedorMovimientoService, FacturaRendicionDetalleService facturaRendicionDetalleService) {
        this.proveedorMovimientoService = proveedorMovimientoService;
        this.facturaRendicionDetalleService = facturaRendicionDetalleService;
    }

    public List<ProveedorMovimiento> findAllByProveedorIdSinRendicion(Long proveedorId) {
        Map<String, ProveedorMovimiento> proveedorMovimientos = new HashMap<>();

        proveedorMovimientoService.findAllByProveedorId(proveedorId).forEach(movimiento ->
                proveedorMovimientos.put(movimiento.comprobanteKey(), movimiento)
        );

        // Removemos los movimientos que tienen rendición
        facturaRendicionDetalleService.findAllByProveedorId(proveedorId).forEach(detalle ->
                proveedorMovimientos.remove(detalle.comprobanteKey())
        );

        return new ArrayList<>(proveedorMovimientos.values());
    }

}
