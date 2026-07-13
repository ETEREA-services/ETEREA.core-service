package eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper;

import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.dto.ValorResponse;
import org.springframework.stereotype.Component;

@Component
public class ValorDtoMapper {

    public ValorResponse toResponse(Valor domain) {
        if (domain == null) {
            return null;
        }
        return ValorResponse.builder()
                .valorId(domain.getValorId())
                .concepto(domain.getConcepto())
                .negocioId(domain.getNegocioId())
                .compras(domain.getCompras())
                .ventas(domain.getVentas())
                .numerable(domain.getNumerable())
                .duplicados(domain.getDuplicados())
                .fechaEmision(domain.getFechaEmision())
                .fechaVencimiento(domain.getFechaVencimiento())
                .numeroCuenta(domain.getNumeroCuenta())
                .titular(domain.getTitular())
                .banco(domain.getBanco())
                .chequeTercero(domain.getChequeTercero())
                .cuentaCorriente(domain.getCuentaCorriente())
                .retencionIngresosBrutos(domain.getRetencionIngresosBrutos())
                .retencionGanancias(domain.getRetencionGanancias())
                .retencionIva(domain.getRetencionIva())
                .autoNumerable(domain.getAutoNumerable())
                .monedaId(domain.getMonedaId())
                .invisible(domain.getInvisible())
                .restaurant(domain.getRestaurant())
                .tarjeta(domain.getTarjeta())
                .build();
    }

}
