package eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.articulo.infrastructure.persistence.mapper.ArticuloMapper;
import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.entity.ArticuloMovimientoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ArticuloMovimientoMapper {

    private final ArticuloMapper articuloMapper;

    public ArticuloMovimiento toDomain(ArticuloMovimientoEntity entity) {
        if (entity == null) return null;
        return ArticuloMovimiento.builder()
                .articuloMovimientoId(entity.getArticuloMovimientoId())
                .clienteMovimientoId(entity.getClienteMovimientoId())
                .stockMovimientoId(entity.getStockMovimientoId())
                .tenenciaMovimientoId(entity.getTenenciaMovimientoId())
                .centroStockId(entity.getCentroStockId())
                .comprobanteId(entity.getComprobanteId())
                .item(entity.getItem())
                .articuloId(entity.getArticuloId())
                .negocioId(entity.getNegocioId())
                .cantidad(entity.getCantidad())
                .precioUnitario(entity.getPrecioUnitario())
                .precioUnitarioSinIva(entity.getPrecioUnitarioSinIva())
                .precioUnitarioConIva(entity.getPrecioUnitarioConIva())
                .numeroCuenta(entity.getNumeroCuenta())
                .iva105(entity.getIva105())
                .exento(entity.getExento())
                .fechaMovimiento(entity.getFechaMovimiento())
                .fechaFactura(entity.getFechaFactura())
                .nivel(entity.getNivel())
                .cierreCajaId(entity.getCierreCajaId())
                .cierreRestaurantId(entity.getCierreRestaurantId())
                .precioCompra(entity.getPrecioCompra())
                .precioValuacion(entity.getPrecioValuacion())
                .mozoId(entity.getMozoId())
                .comision(entity.getComision())
                .trackUuid(entity.getTrackUuid())
                .totalConIva(entity.getTotalConIva())
                .totalSinIva(entity.getTotalSinIva())
                .articulo(articuloMapper.toDomain(entity.getArticulo()))
                .build();
    }

    public ArticuloMovimientoEntity toEntity(ArticuloMovimiento domain) {
        if (domain == null) return null;
        return ArticuloMovimientoEntity.builder()
                .articuloMovimientoId(domain.getArticuloMovimientoId())
                .clienteMovimientoId(domain.getClienteMovimientoId())
                .stockMovimientoId(domain.getStockMovimientoId() == null ? 0 : domain.getStockMovimientoId())
                .tenenciaMovimientoId(domain.getTenenciaMovimientoId() == null ? 0 : domain.getTenenciaMovimientoId())
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
                .nivel(domain.getNivel() == null ? 0 : domain.getNivel())
                .cierreCajaId(domain.getCierreCajaId() == null ? 0 : domain.getCierreCajaId())
                .cierreRestaurantId(domain.getCierreRestaurantId()  == null ? 0 : domain.getCierreRestaurantId())
                .precioCompra(domain.getPrecioCompra())
                .precioValuacion(domain.getPrecioValuacion() == null ? BigDecimal.ZERO : domain.getPrecioValuacion())
                .mozoId(domain.getMozoId() == null ? 0 : domain.getMozoId())
                .comision(domain.getComision() == null ? BigDecimal.ZERO : domain.getComision())
                .trackUuid(domain.getTrackUuid())
                .totalConIva(domain.getTotalConIva() == null ? BigDecimal.ZERO : domain.getTotalConIva())
                .totalSinIva(domain.getTotalSinIva() == null ? BigDecimal.ZERO : domain.getTotalSinIva())
                .articulo(articuloMapper.toEntity(domain.getArticulo()))
                .build();
    }
}
