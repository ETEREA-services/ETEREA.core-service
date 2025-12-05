package eterea.core.service.hexagonal.empresa.application.service;

import eterea.core.service.hexagonal.empresa.domain.model.Empresa;
import eterea.core.service.hexagonal.empresa.domain.ports.in.GetLastEmpresaUseCase;
import eterea.core.service.hexagonal.empresa.domain.ports.out.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmpresaService implements GetLastEmpresaUseCase {

    private final EmpresaRepository empresaRepository;

    @Override
    public Optional<Empresa> findLast() {
        return empresaRepository.findLast()
                .map(empresa -> {
                    if (empresa.getBusinessId() == null) {
                        empresa.setBusinessId(UUID.randomUUID());
                        empresaRepository.save(empresa);
                    }
                    return empresa;
                });
    }

}
