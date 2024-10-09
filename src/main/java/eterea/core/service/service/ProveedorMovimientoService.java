package eterea.core.service.service;

import eterea.core.service.kotlin.model.ProveedorMovimiento;
import eterea.core.service.kotlin.repository.ProveedorMovimientoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProveedorMovimientoService {

    private final ProveedorMovimientoRepository repository;

    public ProveedorMovimientoService(ProveedorMovimientoRepository repository) {
        this.repository = repository;
    }

    public List<ProveedorMovimiento> findAllByProveedorId(Long proveedorId) {
        return repository.findAllByProveedorIdOrderByFechaComprobante(proveedorId);
    }

}
