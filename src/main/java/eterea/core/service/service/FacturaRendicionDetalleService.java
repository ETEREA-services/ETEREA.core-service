package eterea.core.service.service;

import eterea.core.service.kotlin.model.FacturaRendicionDetalle;
import eterea.core.service.kotlin.repository.FacturaRendicionDetalleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacturaRendicionDetalleService {

    private final FacturaRendicionDetalleRepository repository;

    public List<FacturaRendicionDetalle> findAllByProveedorId(Long proveedorId) {
        return repository.findAllByProveedorId(proveedorId);
    }
}
