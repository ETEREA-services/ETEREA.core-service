package eterea.core.api.rest.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public interface ICuentaMovimientoRepositoryCustom {

    public BigDecimal calculateTotalByNumeroCuentaAndDebitaAndIncluyeInflacionAndFechaBetween(Long numeroCuenta, Integer debita, Boolean incluyeInflacion, OffsetDateTime desde, OffsetDateTime hasta);

}
