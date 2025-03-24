package eterea.core.service.model.dto;

import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.model.ClienteMovimiento;
import lombok.Data;

@Data
public class DatoUnaFacturaDto {

        private ClienteMovimiento clienteMovimiento;
        private ArticuloMovimiento articuloMovimiento;

}
