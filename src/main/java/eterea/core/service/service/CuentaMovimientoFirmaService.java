package eterea.core.service.service;

import eterea.core.service.kotlin.exception.CuentaMovimientoFirmaException;
import eterea.core.service.kotlin.model.CuentaMovimientoFirma;
import eterea.core.service.kotlin.repository.CuentaMovimientoFirmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CuentaMovimientoFirmaService {

    private final CuentaMovimientoFirmaRepository repository;

    public CuentaMovimientoFirma findByAsiento(OffsetDateTime fechaContable, Integer ordenContable) {
        return Objects.requireNonNull(repository.findByFechaContableAndOrdenContable(fechaContable, ordenContable)).orElseThrow(() -> new CuentaMovimientoFirmaException(fechaContable, ordenContable));
    }

}
