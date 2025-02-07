package eterea.core.service.model.dto.programadia.mapper;

import java.util.function.Function;

import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.model.dto.programadia.ProveedorDto;

public class ProveedorToDtoMapper implements Function<Proveedor, ProveedorDto> {
   @Override
   public ProveedorDto apply(Proveedor proveedor) {
      return new ProveedorDto(proveedor.getProveedorId());
   }
}