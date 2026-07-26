package eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.ports.out;

import eterea.core.service.hexagonal.cierrecaja.cierrecajamovimiento.domain.model.CierreCaja;

import java.util.Optional;

public interface CierreCajaRepository {

    Optional<CierreCaja> findByCierreCajaId(Long cierreCajaId);

    Optional<CierreCaja> findTopByFechaTope();

}
