package eterea.core.service.model.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record CobroInternoDto(Long valorMovimientoId, Long cierreCajaId, Integer negocioId,
                              OffsetDateTime fechaContable, BigDecimal importe, Long movClieId, Long clienteId,
                              Long nroPlanCta, Integer puntoVenta, String clienteCuit, String clienteRazon,
                              String clienteNombre, String concepto, Integer tipoComprobId, String tipoComprobDesc
                              // OffsetDateTime cierreCajaFecha, OffsetDateTime cierreCajaFechaTope, String cierreCajaUsuario
) {
}
