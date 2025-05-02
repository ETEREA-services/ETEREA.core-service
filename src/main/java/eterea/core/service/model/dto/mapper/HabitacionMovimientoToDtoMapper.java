package eterea.core.service.model.dto.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.HabitacionMovimiento;
import eterea.core.service.model.dto.HabitacionMovimientoResponseDto;

@Component
public class HabitacionMovimientoToDtoMapper implements Function<HabitacionMovimiento, HabitacionMovimientoResponseDto> {
   
   @Override
   public HabitacionMovimientoResponseDto apply(HabitacionMovimiento habitacionMovimiento) {
      return new HabitacionMovimientoResponseDto(
         habitacionMovimiento.getHabitacionMovimientoId(),
         habitacionMovimiento.getNumeroReserva(),
         habitacionMovimiento.getFechaIngreso(),
         habitacionMovimiento.getFechaSalida(),
         habitacionMovimiento.getTarifaId(),
         habitacionMovimiento.getConceptoTarifa(),
         habitacionMovimiento.getPrecioUnitarioTarifa(),
         habitacionMovimiento.getCantidadPax(),
         habitacionMovimiento.getDiasFacturados(),
         habitacionMovimiento.getImporteFacturado(),
         habitacionMovimiento.getFechaOperacion(),
         habitacionMovimiento.getFechaVencimiento(),
         habitacionMovimiento.getItem(),
         habitacionMovimiento.getTarifaStandard(),
         habitacionMovimiento.getCantidadPaxMayor(),
         habitacionMovimiento.getCantidadPaxMenor(),
         habitacionMovimiento.getObservaciones(),
         habitacionMovimiento.getCliente().getClienteId(),
         habitacionMovimiento.getEstadoReserva().getLetraComprobante(),
         habitacionMovimiento.getHabitacion().getNumero()
      );
   }
   
}
