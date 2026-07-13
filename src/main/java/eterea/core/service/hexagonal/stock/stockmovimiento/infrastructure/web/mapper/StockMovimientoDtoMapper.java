package eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.stock.stockmovimiento.domain.model.StockMovimiento;
import eterea.core.service.hexagonal.stock.stockmovimiento.infrastructure.web.dto.StockMovimientoResponse;
import org.springframework.stereotype.Component;

@Component
public class StockMovimientoDtoMapper {

    public StockMovimientoResponse toResponse(StockMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return StockMovimientoResponse.builder()
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
