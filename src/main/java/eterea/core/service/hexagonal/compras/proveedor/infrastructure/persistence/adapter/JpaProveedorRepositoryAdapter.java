package eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.adapter;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.hexagonal.compras.proveedor.domain.ports.out.ProveedorRepository;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.mapper.ProveedorMapper;
import eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.repository.JpaProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JpaProveedorRepositoryAdapter implements ProveedorRepository {

    private final JpaProveedorRepository jpaRepository;
    private final ProveedorMapper proveedorMapper;

    @Override
    public Optional<Proveedor> findByProveedorId(Integer proveedorId) {
        return jpaRepository.findByProveedorId(proveedorId)
                .map(proveedorMapper::toDomain);
    }

}
