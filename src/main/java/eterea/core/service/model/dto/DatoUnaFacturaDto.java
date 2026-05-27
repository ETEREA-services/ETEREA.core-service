package eterea.core.service.model.dto;

import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.entity.ArticuloMovimientoEntity;
import eterea.core.service.model.ClienteMovimiento;
import lombok.Data;

@Data
public class DatoUnaFacturaDto {

        private ClienteMovimiento clienteMovimiento;
        private ArticuloMovimiento articuloMovimiento;

}
