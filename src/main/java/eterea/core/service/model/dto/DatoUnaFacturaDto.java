package eterea.core.service.model.dto;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import lombok.Data;

@Data
public class DatoUnaFacturaDto {

        private ClienteMovimiento clienteMovimiento;
        private ArticuloMovimiento articuloMovimiento;

}
