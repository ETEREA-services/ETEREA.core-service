package eterea.core.service.service;

import eterea.core.service.kotlin.exception.CuentaMovimientoFirmaException;
import eterea.core.service.kotlin.model.CuentaMovimientoFirma;
import eterea.core.service.kotlin.repository.CuentaMovimientoFirmaRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Objects;

@Service
public class CuentaMovimientoFirmaService {

    private final CuentaMovimientoFirmaRepository repository;

    public CuentaMovimientoFirmaService(CuentaMovimientoFirmaRepository repository) {
        this.repository = repository;
    }

    public CuentaMovimientoFirma findByAsiento(OffsetDateTime fechaContable, Integer ordenContable) {
        return Objects.requireNonNull(repository.findByFechaContableAndOrdenContable(fechaContable, ordenContable)).orElseThrow(() -> new CuentaMovimientoFirmaException(fechaContable, ordenContable));
    }

}
