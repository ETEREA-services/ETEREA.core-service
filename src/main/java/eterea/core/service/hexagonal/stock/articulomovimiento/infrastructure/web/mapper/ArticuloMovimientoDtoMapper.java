package eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.stock.articulo.infrastructure.web.mapper.ArticuloDtoMapper;
import eterea.core.service.hexagonal.stock.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.stock.articulomovimiento.infrastructure.web.dto.ArticuloMovimientoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ArticuloMovimientoDtoMapper {

    private final ArticuloDtoMapper articuloDtoMapper;

    public ArticuloMovimientoResponse toResponse(ArticuloMovimiento domain) {
        if (domain == null) return null;
        return ArticuloMovimientoResponse.builder()
                .articuloMovimientoId(domain.getArticuloMovimientoId())
                .clienteMovimientoId(domain.getClienteMovimientoId())
                .stockMovimientoId(domain.getStockMovimientoId())
                .tenenciaMovimientoId(domain.getTenenciaMovimientoId())
                .centroStockId(domain.getCentroStockId())
                .comprobanteId(domain.getComprobanteId())
                .item(domain.getItem())
                .articuloId(domain.getArticuloId())
                .negocioId(domain.getNegocioId())
                .cantidad(domain.getCantidad())
                .precioUnitario(domain.getPrecioUnitario())
                .precioUnitarioSinIva(domain.getPrecioUnitarioSinIva())
                .precioUnitarioConIva(domain.getPrecioUnitarioConIva())
                .numeroCuenta(domain.getNumeroCuenta())
                .iva105(domain.getIva105())
                .exento(domain.getExento())
                .fechaMovimiento(domain.getFechaMovimiento())
                .fechaFactura(domain.getFechaFactura())
                .nivel(domain.getNivel())
                .cierreCajaId(domain.getCierreCajaId())
                .cierreRestaurantId(domain.getCierreRestaurantId())
                .precioCompra(domain.getPrecioCompra())
                .precioValuacion(domain.getPrecioValuacion())
                .mozoId(domain.getMozoId())
                .comision(domain.getComision())
                .trackUuid(domain.getTrackUuid())
                .totalConIva(domain.getTotalConIva())
                .totalSinIva(domain.getTotalSinIva())
                .articulo(articuloDtoMapper.toResponse(domain.getArticulo()))
                .build();
    }
}
