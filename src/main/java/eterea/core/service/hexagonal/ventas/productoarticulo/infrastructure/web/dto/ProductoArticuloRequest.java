package eterea.core.service.hexagonal.ventas.productoarticulo.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductoArticuloRequest {

    private Integer negocioId;

    @NotNull
    private Integer productoId;

    @NotNull
    @NotBlank
    private String articuloId;

}
