package eterea.core.service.hexagonal.compras.proveedor.domain.ports.in;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;

import java.util.Optional;

public interface FindProveedorByIdUseCase {

    Optional<Proveedor> findByProveedorId(Integer proveedorId);

}
