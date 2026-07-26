package eterea.core.service.hexagonal.comprobante.application.usecases;

import eterea.core.service.hexagonal.comprobante.application.exception.ComprobanteException;
import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import eterea.core.service.hexagonal.comprobante.domain.ports.in.FindComprobanteByIdUseCase;
import eterea.core.service.hexagonal.comprobante.domain.ports.out.ComprobanteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindComprobanteByIdUseCaseImpl implements FindComprobanteByIdUseCase {

    private final ComprobanteRepository repository;

    @Override
    public Comprobante findByComprobanteId(Integer comprobanteId) {
        log.debug("Processing FindComprobanteByIdUseCaseImpl.findByComprobanteId");
        return repository.findByComprobanteId(comprobanteId)
                .orElseThrow(() -> new ComprobanteException(comprobanteId));
    }
}
