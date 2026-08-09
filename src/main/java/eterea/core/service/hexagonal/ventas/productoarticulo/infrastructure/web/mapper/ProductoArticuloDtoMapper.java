package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.mapper;

import eterea.core.service.hexagonal.ventas.productoarticulo.domain.model.ProductoArticulo;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.dto.ProductoArticuloRequest;
import eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.dto.ProductoArticuloResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductoArticuloDtoMapper {

    public ProductoArticulo toDomain(ProductoArticuloRequest request) {
        if (request == null) {
            return null;
        }
        return ProductoArticulo.builder()
                .negocioId(request.getNegocioId())
                .productoId(request.getProductoId())
                .articuloId(request.getArticuloId())
                .build();
    }

    public ProductoArticuloResponse toResponse(ProductoArticulo domain) {
        if (domain == null) {
            return null;
        }
        return ProductoArticuloResponse.builder()
                .productoarticuloId(domain.getProductoarticuloId())
                .negocioId(domain.getNegocioId())
                .productoId(domain.getProductoId())
                .articuloId(domain.getArticuloId())
                .build();
    }

}
