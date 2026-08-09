package eterea.core.service.hexagonal.ventas.productoarticulo.domain.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoArticulo {

    private Long productoarticuloId;
    private Integer negocioId;
    private Integer productoId;
    private String articuloId;

}
