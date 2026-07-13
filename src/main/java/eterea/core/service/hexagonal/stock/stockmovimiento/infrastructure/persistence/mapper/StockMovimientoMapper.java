package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.persistence.entity.StockMovimientoEntity;
import org.springframework.stereotype.Component;

@Component
public class StockMovimientoMapper {

    public StockMovimiento toDomain(StockMovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return StockMovimiento.builder()
                .stockMovimientoId(entity.getStockMovimientoId())
                .comprobanteId(entity.getComprobanteId())
                .numeroComprobanteInterno(entity.getNumeroComprobanteInterno())
                .negocioId(entity.getNegocioId())
                .negocioIdDesde(entity.getNegocioIdDesde())
                .centroStockIdDesde(entity.getCentroStockIdDesde())
                .negocioIdHasta(entity.getNegocioIdHasta())
                .centroStockIdHasta(entity.getCentroStockIdHasta())
                .centroStockIdHastaNombre(entity.getCentroStockIdHastaNombre())
                .fechaRegistro(entity.getFechaRegistro())
                .proveedorId(entity.getProveedorId())
                .clienteId(entity.getClienteId())
                .legajo(entity.getLegajo())
                .fechaComprobante(entity.getFechaComprobante())
                .comprobanteIdFactura(entity.getComprobanteIdFactura())
                .prefijoFactura(entity.getPrefijoFactura())
                .numeroComprobanteFactura(entity.getNumeroComprobanteFactura())
                .importe(entity.getImporte())
                .letraComanda(entity.getLetraComanda())
                .observaciones(entity.getObservaciones())
                .cierreCajaId(entity.getCierreCajaId())
                .cierreRestaurantId(entity.getCierreRestaurantId())
                .nivel(entity.getNivel())
                .fechaContable(entity.getFechaContable())
                .ordenContable(entity.getOrdenContable())
                .negocioIdOtro(entity.getNegocioIdOtro())
                .generacionAutomatica(entity.getGeneracionAutomatica())
                .pendiente(entity.getPendiente())
                .rechazada(entity.getRechazada())
                .facturaProveedor(entity.getFacturaProveedor())
                .netoFactura(entity.getNetoFactura())
                .netoRegistrado(entity.getNetoRegistrado())
                .build();
    }

    public StockMovimientoEntity toEntity(StockMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return StockMovimientoEntity.builder()
                .stockMovimientoId(domain.getStockMovimientoId())
                .comprobanteId(domain.getComprobanteId())
                .numeroComprobanteInterno(domain.getNumeroComprobanteInterno())
                .negocioId(domain.getNegocioId())
                .negocioIdDesde(domain.getNegocioIdDesde())
                .centroStockIdDesde(domain.getCentroStockIdDesde())
                .negocioIdHasta(domain.getNegocioIdHasta())
                .centroStockIdHasta(domain.getCentroStockIdHasta())
                .centroStockIdHastaNombre(domain.getCentroStockIdHastaNombre())
                .fechaRegistro(domain.getFechaRegistro())
                .proveedorId(domain.getProveedorId())
                .clienteId(domain.getClienteId())
                .legajo(domain.getLegajo())
                .fechaComprobante(domain.getFechaComprobante())
                .comprobanteIdFactura(domain.getComprobanteIdFactura())
                .prefijoFactura(domain.getPrefijoFactura())
                .numeroComprobanteFactura(domain.getNumeroComprobanteFactura())
                .importe(domain.getImporte())
                .letraComanda(domain.getLetraComanda())
                .observaciones(domain.getObservaciones())
                .cierreCajaId(domain.getCierreCajaId())
                .cierreRestaurantId(domain.getCierreRestaurantId())
                .nivel(domain.getNivel())
                .fechaContable(domain.getFechaContable())
                .ordenContable(domain.getOrdenContable())
                .negocioIdOtro(domain.getNegocioIdOtro())
                .generacionAutomatica(domain.getGeneracionAutomatica())
                .pendiente(domain.getPendiente())
                .rechazada(domain.getRechazada())
                .facturaProveedor(domain.getFacturaProveedor())
                .netoFactura(domain.getNetoFactura())
                .netoRegistrado(domain.getNetoRegistrado())
                .build();
    }

}
