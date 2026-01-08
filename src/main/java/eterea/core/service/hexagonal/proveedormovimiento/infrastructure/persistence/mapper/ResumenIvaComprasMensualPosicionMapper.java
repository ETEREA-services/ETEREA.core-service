package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensualPosicion;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto.ResumenIvaComprasMensualPosicionDto;
import org.springframework.stereotype.Component;

@Component
public class ResumenIvaComprasMensualPosicionMapper {

    public ResumenIvaComprasMensualPosicion toDomain(ResumenIvaComprasMensualPosicionDto resumen) {
        if (resumen == null) {
            return null;
        }
        return ResumenIvaComprasMensualPosicion.builder()
                .negocioId(resumen.getNegocioId())
                .anho(resumen.getAnho())
                .mes(resumen.getMes())
                .posicion(resumen.getPosicion())
                .neto(resumen.getNeto())
                .facturadoC(resumen.getFacturadoC())
                .gastosNoGravados(resumen.getGastosNoGravados())
                .iva21(resumen.getIva21())
                .iva27(resumen.getIva27())
                .iva105(resumen.getIva105())
                .percepcionIva(resumen.getPercepcionIva())
                .percepcionIngresosBrutos(resumen.getPercepcionIngresosBrutos())
                .total(resumen.getTotal())
                .build();
    }

}
