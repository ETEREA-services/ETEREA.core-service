package eterea.core.service.model.dto.programadia;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Grupo;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.kotlin.model.Articulo;

@Component
public class GrupoToDtoMapper {

   private final ProductoToDtoMapper productoToDtoMapper;

   public GrupoToDtoMapper(ProductoToDtoMapper productoToDtoMapper) {
      this.productoToDtoMapper = productoToDtoMapper;
   }

   public ProgramaDiaGrupoDto toDto(Grupo grupo, List<Producto> productos, Map<Integer, List<Articulo>> articulosByProducto) {
      return new ProgramaDiaGrupoDto(
            grupo.getGrupoId(),
            grupo.getNombre(),
            productos.stream()
                .map(producto -> productoToDtoMapper.toDto(
                    producto, 
                    articulosByProducto.getOrDefault(producto.getProductoId(), List.of())
                ))
                .toList());
   }

}
