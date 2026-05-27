package eterea.core.service.hexagonal.articulo.application.usecases;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.articulo.domain.ports.in.CalculateTotalesByArticuloUseCase;
import eterea.core.service.hexagonal.articulo.domain.ports.in.FindArticuloByArticuloIdUseCase;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.TotalArticuloResponse;
import eterea.core.service.tool.ToolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@RequiredArgsConstructor
public class CalculateTotalesByArticuloUseCaseImpl implements CalculateTotalesByArticuloUseCase {

    private final FindArticuloByArticuloIdUseCase findArticuloByArticuloIdUseCase;

    @Override
    public TotalArticuloResponse calculateTotales(String articuloId, BigDecimal cantidad, BigDecimal precioUnitarioConIva) {
        Articulo articulo = findArticuloByArticuloIdUseCase.findByArticuloId(articuloId);
        BigDecimal alicuotaIva = ToolService.IVA_21;
        BigDecimal factorIva = ToolService.FACTOR_IVA_21;
        if (articulo.getIva105() == 1) {
            alicuotaIva = ToolService.IVA_105;
            factorIva = ToolService.FACTOR_IVA_105;
        }
        if (articulo.getExento() == 1) {
            alicuotaIva = BigDecimal.ONE;
            factorIva = BigDecimal.ONE;
        }
        var totalConIva = precioUnitarioConIva.multiply(cantidad).setScale(4, RoundingMode.HALF_UP);
        return TotalArticuloResponse.builder()
                .articuloId(articuloId)
                .cantidad(cantidad)
                .precioUnitarioConIva(precioUnitarioConIva)
                .precioUnitarioSinIva(precioUnitarioConIva.divide(factorIva, 4, RoundingMode.HALF_UP))
                .precioTotalConIva(totalConIva)
                .precioTotalSinIva(totalConIva.divide(factorIva, 4, RoundingMode.HALF_UP))
                .iva105(articulo.getIva105())
                .exento(articulo.getExento())
                .build();
    }

}
