package eterea.core.service.service;

import eterea.core.service.kotlin.model.FacturaRendicionDetalle;
import eterea.core.service.kotlin.repository.FacturaRendicionDetalleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacturaRendicionDetalleService {

    private final FacturaRendicionDetalleRepository repository;

    public FacturaRendicionDetalleService(FacturaRendicionDetalleRepository repository) {
        this.repository = repository;
    }

    public List<FacturaRendicionDetalle> findAllByProveedorId(Long proveedorId) {
        return repository.findAllByProveedorId(proveedorId);
    }
}
