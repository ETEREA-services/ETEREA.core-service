package eterea.core.service.model.dto.programadia;

import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import eterea.core.service.kotlin.model.Producto;
import eterea.core.service.kotlin.model.Articulo;

@Component
public class ProductoToDtoMapper {
    private final ArticuloToDtoMapper articuloToDtoMapper;

    public ProductoToDtoMapper(ArticuloToDtoMapper articuloToDtoMapper) {
        this.articuloToDtoMapper = articuloToDtoMapper;
    }

    public ProgramaDiaProductoDto toDto(Producto producto, List<Articulo> articulos) {
        return new ProgramaDiaProductoDto(
            producto.getProductoId(),
            producto.getNombre(),
            producto.getObservaciones(),
            articulos.stream()
                .map(articuloToDtoMapper)
                .toList());
    }
}
