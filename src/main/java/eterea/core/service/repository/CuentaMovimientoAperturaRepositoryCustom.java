package eterea.core.service.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface CuentaMovimientoAperturaRepositoryCustom {

    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, Integer debita, OffsetDateTime desde, OffsetDateTime hasta);

}
