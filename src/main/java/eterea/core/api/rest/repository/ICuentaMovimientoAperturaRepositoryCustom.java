package eterea.core.api.rest.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface ICuentaMovimientoAperturaRepositoryCustom {

    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndFechaBetween(Long numeroCuenta, Integer debita, OffsetDateTime desde, OffsetDateTime hasta);

}
