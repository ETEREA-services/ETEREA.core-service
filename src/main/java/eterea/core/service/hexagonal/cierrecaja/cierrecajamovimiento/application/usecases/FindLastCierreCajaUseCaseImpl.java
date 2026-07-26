package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.usecases;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.exception.CierreCajaException;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.in.FindLastCierreCajaUseCase;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.out.CierreCajaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindLastCierreCajaUseCaseImpl implements FindLastCierreCajaUseCase {

    private final CierreCajaRepository repository;

    @Override
    public CierreCaja findLast() {
        log.debug("Processing FindLastCierreCajaUseCaseImpl.findLast");
        return repository.findTopByFechaTope()
                .orElseThrow(() -> new CierreCajaException(0L));
    }
}
