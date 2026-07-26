package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindAllComprobantesAsociablesUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllComprobantesAsociablesUseCaseImpl implements FindAllComprobantesAsociablesUseCase {

    private static final Integer MODULO_ASOCIABLES = 3;
    private static final Byte LIBRO_IVA_ACTIVO = (byte) 1;

    private final ComprobanteRepository repository;

    @Override
    public List<Comprobante> findAllAsociables() {
        return repository.findAllByModuloAndLibroIva(MODULO_ASOCIABLES, LIBRO_IVA_ACTIVO);
    }

    @Override
    public List<Comprobante> findAllAsociables(Byte debita) {
        if (debita == null) {
            return findAllAsociables();
        }
        return repository.findAllByModuloAndDebitaAndLibroIva(MODULO_ASOCIABLES, (byte) (1 - debita), LIBRO_IVA_ACTIVO);
    }
}
