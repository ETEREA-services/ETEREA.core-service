package eterea.core.service.hexagonal.invoicedata.infrastructure.mapper;

import eterea.core.service.hexagonal.invoicedata.infrastructure.dto.ArticuloResponse;
import eterea.core.service.kotlin.model.Articulo;
import org.springframework.stereotype.Component;

@Component
public class ArticuloMapper {

    public ArticuloResponse toResponse(Articulo articulo) {
        if (articulo == null) {
            return null;
        }
        return ArticuloResponse.builder()
                .descripcion(articulo.getDescripcion())
                .build();
    }

}
