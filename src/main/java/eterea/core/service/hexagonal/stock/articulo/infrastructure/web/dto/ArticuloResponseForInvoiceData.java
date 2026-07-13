package eterea.core.service.hexagonal.stock.articulo.infrastructure.web.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ArticuloResponseForInvoiceData {

    private String descripcion;

}
