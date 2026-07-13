package eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.dto.ArticuloMovimientoResponse;
import org.springframework.stereotype.Component;

@Component
public class ArticuloMovimientoDtoMapper {

    public ArticuloMovimientoResponse toResponse(ArticuloMovimiento domain) {
        if (domain == null) return null;
        return ArticuloMovimientoResponse.builder()
                .articuloMovimientoId(domain.getArticuloMovimientoId())
                .clienteMovimientoId(domain.getClienteMovimientoId())
                .stockMovimientoId(domain.getStockMovimientoId())
                .centroStockId(domain.getCentroStockId())
                .comprobanteId(domain.getComprobanteId())
                .item(domain.getItem())
                .articuloId(domain.getArticuloId())
                .negocioId(domain.getNegocioId())
                .cantidad(domain.getCantidad())
                .precioUnitario(domain.getPrecioUnitario())
                .precioUnitarioSinIva(domain.getPrecioUnitarioSinIva())
                .precioUnitarioConIva(domain.getPrecioUnitarioConIva())
                .fechaMovimiento(domain.getFechaMovimiento())
                .totalConIva(domain.getTotalConIva())
                .totalSinIva(domain.getTotalSinIva())
                .build();
    }
}
