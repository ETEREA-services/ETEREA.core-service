package eterea.core.service.hexagonal.compras.proveedor.application.service;

import eterea.core.service.hexagonal.compras.proveedor.application.exception.ProveedorException;
import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.hexagonal.compras.proveedor.domain.ports.in.FindProveedorByIdUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final FindProveedorByIdUseCase findProveedorByIdUseCase;

    public Proveedor findByProveedorId(Integer proveedorId) {
        return findProveedorByIdUseCase.findByProveedorId(proveedorId)
                .orElseThrow(() -> new ProveedorException(proveedorId));
    }

}
