package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.usecases;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.exception.CierreCajaException;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.in.FindCierreCajaByIdUseCase;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.out.CierreCajaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class FindCierreCajaByIdUseCaseImpl implements FindCierreCajaByIdUseCase {

    private final CierreCajaRepository repository;

    @Override
    public CierreCaja findByCierreCajaId(Long cierreCajaId) {
        log.debug("Processing FindCierreCajaByIdUseCaseImpl.findByCierreCajaId");
        return repository.findByCierreCajaId(cierreCajaId)
                .orElseThrow(() -> new CierreCajaException(cierreCajaId));
    }
}
