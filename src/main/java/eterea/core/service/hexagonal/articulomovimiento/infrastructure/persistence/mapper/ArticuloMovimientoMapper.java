package eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.articulo.infrastructure.persistence.mapper.ArticuloMapper;
import eterea.core.service.hexagonal.articulomovimiento.domain.model.ArticuloMovimiento;
import eterea.core.service.hexagonal.articulomovimiento.infrastructure.persistence.entity.ArticuloMovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class ArticuloMovimientoMapper {

    private final ArticuloMapper articuloMapper;

    public ArticuloMovimientoMapper(ArticuloMapper articuloMapper) {
        this.articuloMapper = articuloMapper;
    }

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
                .articulo(articuloMapper.toEntity(domain.getArticulo()))
                .build();
    }
}
