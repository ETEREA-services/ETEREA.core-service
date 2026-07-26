package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindAllComprobantesByModuloUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllComprobantesByModuloUseCaseImpl implements FindAllComprobantesByModuloUseCase {

    private final ComprobanteRepository repository;

    @Override
    public List<Comprobante> findAllByModulo(Integer modulo, Byte debita, Integer comprobanteId) {
        boolean isDebitaFilter = debita != null && debita < 2;
        boolean isComprobanteIdFilter = comprobanteId != null && comprobanteId > 0;

        if (isDebitaFilter && isComprobanteIdFilter) {
            return repository.findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(modulo, (byte) 0,
                    (byte) 0, debita, comprobanteId);
        }
        if (isDebitaFilter) {
            return repository.findAllByModuloAndTransferirAndInvisibleAndDebita(modulo, (byte) 0, (byte) 0, (byte) 0);
        }
        if (isComprobanteIdFilter) {
            return repository.findAllByModuloAndTransferirAndInvisibleAndComprobanteId(modulo, (byte) 0, (byte) 0,
                    comprobanteId);
        }
        return repository.findAllByModuloAndTransferirAndInvisible(modulo, (byte) 0, (byte) 0);
    }
}
