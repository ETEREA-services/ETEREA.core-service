package eterea.core.service.model.dto;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockAndArticulosDto {

    private StockMovimiento stockMovimiento;
    private List<ArticuloMovimiento> articuloMovimientos;

}
