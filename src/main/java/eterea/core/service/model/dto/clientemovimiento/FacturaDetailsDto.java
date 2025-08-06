package eterea.core.service.model.dto.clientemovimiento;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.Cliente;
import eterea.core.service.kotlin.model.Comprobante;
import eterea.core.service.kotlin.model.CuentaMovimiento;
import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.kotlin.model.Voucher;

public record FacturaDetailsDto(
   Comprobante comprobante,
   Cliente cliente,
   Reserva reserva,
   Voucher voucher,
   Long numeroComprobante,
   OffsetDateTime fechaComprobante,
   BigDecimal importe,
   List<ValorMovimiento> valorMovimientos,
   List<ArticuloMovimiento> articuloMovimientos,
   List<CuentaMovimiento> cuentaMovimientos
) {

   
}
