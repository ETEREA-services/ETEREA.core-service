package eterea.core.service.model.dto.programadia;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Grupo;
import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.kotlin.model.Articulo;
import eterea.core.service.service.ProductoService;
import eterea.core.service.service.ArticuloService;

@Component
public class GrupoToDtoMapper {

   private final ProductoToDtoMapper productoToDtoMapper;
   private final ProductoService productoService;
   private final ArticuloService articuloService;

   public GrupoToDtoMapper(ProductoToDtoMapper productoToDtoMapper, ProductoService productoService, ArticuloService articuloService) {
      this.productoToDtoMapper = productoToDtoMapper;
      this.productoService = productoService;
      this.articuloService = articuloService;
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
