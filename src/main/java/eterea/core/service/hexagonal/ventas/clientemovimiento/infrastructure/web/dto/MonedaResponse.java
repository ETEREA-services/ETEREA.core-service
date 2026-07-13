package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonedaResponse {

    private Integer monedaId;
    private String nombre;
    private String simbolo;
    private Integer valorId;

}
