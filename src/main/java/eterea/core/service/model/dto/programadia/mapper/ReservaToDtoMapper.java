package eterea.core.service.model.dto.programadia.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Reserva;
import eterea.core.service.model.dto.programadia.ReservaDto;

@Component
public class ReservaToDtoMapper implements Function<Reserva, ReservaDto> {

   @Override
   public ReservaDto apply(Reserva reserva) {
      return new ReservaDto(reserva.getReservaId());
   }
}
