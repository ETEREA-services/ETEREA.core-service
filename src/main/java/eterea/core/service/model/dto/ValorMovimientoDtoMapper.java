package eterea.core.service.model.dto;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.ValorMovimiento;

@Component
public class ValorMovimientoDtoMapper implements Function<ValorMovimiento, ValorMovimientoDto> {

   @Override
   public ValorMovimientoDto apply(ValorMovimiento valorMovimiento) {
      return new ValorMovimientoDto(valorMovimiento.getValorMovimientoId(), valorMovimiento.getCierreCajaId(),
            valorMovimiento.getNegocioId(), valorMovimiento.getValor().getConcepto(),
            valorMovimiento.getFechaContable(), valorMovimiento.getImporte());
   }
}