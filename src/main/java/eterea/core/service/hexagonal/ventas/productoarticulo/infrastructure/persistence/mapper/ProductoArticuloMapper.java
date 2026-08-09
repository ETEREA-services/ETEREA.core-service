package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.persistence.entity.ProductoArticuloEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductoArticuloMapper {

    public ProductoArticulo toDomain(ProductoArticuloEntity entity) {
        if (entity == null) {
            return null;
        }
        return ProductoArticulo.builder()
                .productoarticuloId(entity.getProductoarticuloId())
                .negocioId(entity.getNegocioId())
                .productoId(entity.getProductoId())
                .articuloId(entity.getArticuloId())
                .build();
    }

    public ProductoArticuloEntity toEntity(ProductoArticulo domain) {
        if (domain == null) {
            return null;
        }
        return ProductoArticuloEntity.builder()
                .productoarticuloId(domain.getProductoarticuloId())
                .negocioId(domain.getNegocioId())
                .productoId(domain.getProductoId())
                .articuloId(domain.getArticuloId())
                .build();
    }

}
