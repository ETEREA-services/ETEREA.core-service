package eterea.core.service.model.dto;

import java.util.List;

import eterea.core.service.kotlin.model.ValorMovimiento;

public record FacturaMakeRequestDto(
      Long reservaId,
      Integer comprobanteId,
      List<ValorMovimiento> valorMovimientos) {

}
