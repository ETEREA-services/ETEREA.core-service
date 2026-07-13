package eterea.core.service.hexagonal.contable.cuentamovimiento.application.exception;

import java.text.MessageFormat;
import java.time.OffsetDateTime;

public class CuentaMovimientoException extends RuntimeException {

    public CuentaMovimientoException(Long cuentaMovimientoId) {
        super(MessageFormat.format("Cannot find CuentaMovimiento -> {0}", cuentaMovimientoId));
    }

    public CuentaMovimientoException(OffsetDateTime fecha, Integer orden) {
        super(MessageFormat.format("Cannot find CuentaMovimiento -> {0}-{1}", fecha, orden));
    }

    public CuentaMovimientoException(OffsetDateTime fecha) {
        super(MessageFormat.format("Cannot find CuentaMovimiento -> {0}", fecha));
    }

}
