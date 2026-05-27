package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindAllComprobantesByModuloUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindAllComprobantesByModuloUseCaseImpl implements FindAllComprobantesByModuloUseCase {

    private final ComprobanteRepository repository;

    public FindAllComprobantesByModuloUseCaseImpl(ComprobanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Comprobante> findAllByModulo(Integer modulo, Byte debita, Integer comprobanteId) {
        if (debita < 2 && comprobanteId > 0) {
            return repository.findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(modulo, (byte) 0,
                    (byte) 0, debita, comprobanteId);
        }
        if (debita < 2) {
            return repository.findAllByModuloAndTransferirAndInvisibleAndDebita(modulo, (byte) 0, (byte) 0, (byte) 0);
        }
        if (comprobanteId > 0) {
            return repository.findAllByModuloAndTransferirAndInvisibleAndComprobanteId(modulo, (byte) 0, (byte) 0,
                    comprobanteId);
        }
        return repository.findAllByModuloAndTransferirAndInvisible(modulo, (byte) 0, (byte) 0);
    }
}
