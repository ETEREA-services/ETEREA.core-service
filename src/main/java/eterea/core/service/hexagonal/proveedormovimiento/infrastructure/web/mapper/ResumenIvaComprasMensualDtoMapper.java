package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.negocio.application.service.NegocioService;
import eterea.core.service.hexagonal.negocio.domain.model.Negocio;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ResumenIvaComprasMensualResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResumenIvaComprasMensualDtoMapper {

    private final NegocioService negocioService;

    public ResumenIvaComprasMensualResponse toResponse(ResumenIvaComprasMensual resumen) {
        if (resumen == null) {
            return null;
        }
        return ResumenIvaComprasMensualResponse.builder()
                .negocioId(resumen.getNegocioId())
                .negocio(negocioService.getNegocioById(resumen.getNegocioId())
                        .map(Negocio::getNombre)
                        .orElse(""))
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
