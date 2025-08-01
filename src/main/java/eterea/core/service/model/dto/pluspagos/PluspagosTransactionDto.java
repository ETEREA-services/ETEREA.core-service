package eterea.core.service.model.dto.pluspagos;

import java.math.BigDecimal;
import java.util.List;

public record PluspagosTransactionDto(
      Long transaccionId,

      String estado,

      String fecha,

      String moneda,

      String monto,

      String productos,

      String metodoPago,

      String sucursal,

      Long medioPagoId,

      String medioPagoNombre,

      String medioPago,

      String transaccionComercioId,

      Long estadoId,

      Integer cuotas,

      Long userId,

      Long bancoId,

      String tipoPago,

      String informacion,

      BigDecimal montoBruto,

      BigDecimal montoDescuento,

      PluspagosResultDto result,

      String informacionAdicional,

      String informacionAdicionalLink,

      List<PluspagosProductoTransaccionDto> productoTransaccion,

      PluspagosInformacionPagadorDto informacionPagador,

      String fechaPago,

      String detalle,

      String fechaLiq,

      String cftAplicado,

      String panLast4,

      String marcaTarjeta) {
}
