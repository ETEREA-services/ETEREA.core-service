package eterea.core.service.hexagonal.compras.proveedor.domain.ports.out;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;

import java.util.Optional;

public interface ProveedorRepository {

    Optional<Proveedor> findByProveedorId(Integer proveedorId);

}
