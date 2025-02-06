package eterea.core.service.model.dto.programadia;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Articulo;

@Component
public class ArticuloToDtoMapper implements Function<Articulo, ProgramaDiaArticuloDto> {

   @Override
   public ProgramaDiaArticuloDto apply(Articulo articulo) {
      return new ProgramaDiaArticuloDto(articulo.getArticuloId(), articulo.getDescripcion());
   }
}

