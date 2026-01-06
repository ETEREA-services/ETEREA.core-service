package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto;

import java.math.BigDecimal;

public interface ResumenIvaComprasMensualDto {
    Integer getNegocioId();
    Integer getAnho();
    Integer getMes();
    BigDecimal getNeto();
    BigDecimal getFacturadoC();
    BigDecimal getGastosNoGravados();
    BigDecimal getIva21();
    BigDecimal getIva27();
    BigDecimal getIva105();
    BigDecimal getPercepcionIva();
    BigDecimal getPercepcionIngresosBrutos();
    BigDecimal getTotalCalculado();
    BigDecimal getTotal();
}
