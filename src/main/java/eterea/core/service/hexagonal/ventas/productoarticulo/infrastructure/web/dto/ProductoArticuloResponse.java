package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoArticuloResponse {

    private Long productoarticuloId;
    private Integer negocioId;
    private Integer productoId;
    private String articuloId;

}
