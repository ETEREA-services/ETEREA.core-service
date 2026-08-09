package eterea.core.service.hexagonal.compras.proveedor.application.usecases;

import eterea.core.service.hexagonal.compras.proveedor.domain.model.Proveedor;
import eterea.core.service.hexagonal.compras.proveedor.domain.ports.in.FindProveedorByIdUseCase;
import eterea.core.service.hexagonal.compras.proveedor.domain.ports.out.ProveedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindProveedorByIdUseCaseImpl implements FindProveedorByIdUseCase {

    private final ProveedorRepository proveedorRepository;

    @Override
    public Optional<Proveedor> findByProveedorId(Integer proveedorId) {
        return proveedorRepository.findByProveedorId(proveedorId);
    }

}
