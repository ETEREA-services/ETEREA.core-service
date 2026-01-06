package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto.ResumenIvaComprasMensualDto;
import org.springframework.stereotype.Component;

@Component
public class ResumenIvaComprasMensualMapper {

    public ResumenIvaComprasMensual toDomain(ResumenIvaComprasMensualDto resumen) {
        if (resumen == null) {
            return null;
        }
        return ResumenIvaComprasMensual.builder()
                .negocioId(resumen.getNegocioId())
                .anho(resumen.getAnho())
                .mes(resumen.getMes())
                .neto(resumen.getNeto())
                .facturadoC(resumen.getFacturadoC())
                .gastosNoGravados(resumen.getGastosNoGravados())
                .iva21(resumen.getIva21())
                .iva27(resumen.getIva27())
                .iva105(resumen.getIva105())
                .percepcionIva(resumen.getPercepcionIva())
                .percepcionIngresosBrutos(resumen.getPercepcionIngresosBrutos())
                .totalCalculado(resumen.getTotalCalculado())
                .total(resumen.getTotal())
                .build();
    }

}
