package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindAllComprobantesAsociablesUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllComprobantesAsociablesUseCaseImpl implements FindAllComprobantesAsociablesUseCase {

    private final ComprobanteRepository repository;

    public FindAllComprobantesAsociablesUseCaseImpl(ComprobanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comprobante> findAllAsociables() {
        return repository.findAllByModuloAndLibroIva(3, (byte) 1);
    }

    @Override
    public List<Comprobante> findAllAsociables(Byte debita) {
        return repository.findAllByModuloAndDebitaAndLibroIva(3, (byte) (1 - debita), (byte) 1);
    }
}
