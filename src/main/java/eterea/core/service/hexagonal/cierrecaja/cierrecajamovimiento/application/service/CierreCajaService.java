package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.application.service;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.in.FindCierreCajaByIdUseCase;
import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.in.FindLastCierreCajaUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CierreCajaService {

    private final FindCierreCajaByIdUseCase findCierreCajaByIdUseCase;
    private final FindLastCierreCajaUseCase findLastCierreCajaUseCase;

    public CierreCaja findByCierreCajaId(Long cierreCajaId) {
        return findCierreCajaByIdUseCase.findByCierreCajaId(cierreCajaId);
    }

    public CierreCaja findLast() {
        return findLastCierreCajaUseCase.findLast();
    }

}
