package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.exception.ComprobanteException;
import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindComprobanteByIdUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class FindComprobanteByIdUseCaseImpl implements FindComprobanteByIdUseCase {

    private final ComprobanteRepository repository;

    public FindComprobanteByIdUseCaseImpl(ComprobanteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comprobante findByComprobanteId(Integer comprobanteId) {
        log.debug("Processing FindComprobanteByIdUseCaseImpl.findByComprobanteId");
        return repository.findByComprobanteId(comprobanteId)
                .orElseThrow(() -> new ComprobanteException(comprobanteId));
    }
}
