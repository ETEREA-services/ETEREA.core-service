package eterea.core.service.hexagonal.articulo.infrastructure.web.mapper;

import eterea.core.service.hexagonal.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.ArticuloRequest;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.ArticuloResponse;
import eterea.core.service.hexagonal.articulo.infrastructure.web.dto.ArticuloResponseForInvoiceData;
import eterea.core.service.hexagonal.cuenta.infrastructure.web.mapper.CuentaDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticuloDtoMapper {

    private final CuentaDtoMapper cuentaDtoMapper;

    public ArticuloDtoMapper(CuentaDtoMapper cuentaDtoMapper) {
        this.cuentaDtoMapper = cuentaDtoMapper;
    }

    public Articulo toDomain(ArticuloRequest request) {
        if (request == null) return null;
        return Articulo.builder()
                .articuloId(request.getArticuloId())
                .negocioId(request.getNegocioId())
                .descripcion(request.getDescripcion())
                .leyendaVoucher(request.getLeyendaVoucher())
                .precioVentaSinIva(request.getPrecioVentaSinIva())
                .precioVentaConIva(request.getPrecioVentaConIva())
                .numeroCuentaVentas(request.getNumeroCuentaVentas())
                .numeroCuentaCompras(request.getNumeroCuentaCompras())
                .numeroCuentaGastos(request.getNumeroCuentaGastos())
                .centroStockId(request.getCentroStockId())
                .rubroId(request.getRubroId())
                .subRubroId(request.getSubRubroId())
                .precioCompra(request.getPrecioCompra())
                .iva105(request.getIva105())
                .precioCompraNeto(request.getPrecioCompraNeto())
                .exento(request.getExento())
                .stockMinimo(request.getStockMinimo())
                .stockOptimo(request.getStockOptimo())
                .bloqueoCompras(request.getBloqueoCompras())
                .bloqueoStock(request.getBloqueoStock())
                .bloqueoVentas(request.getBloqueoVentas())
                .unidadMedidaId(request.getUnidadMedidaId())
                .conDecimales(request.getConDecimales())
                .ventas(request.getVentas())
                .compras(request.getCompras())
                .unidadMedida(request.getUnidadMedida())
                .conversionId(request.getConversionId())
                .ventaSinStock(request.getVentaSinStock())
                .controlaStock(request.getControlaStock())
                .asientoCostos(request.getAsientoCostos())
                .mascaraBalanza(request.getMascaraBalanza())
                .habilitaIngreso(request.getHabilitaIngreso())
                .comision(request.getComision())
                .prestadorId(request.getPrestadorId())
                .autoNumericoId(request.getAutoNumericoId())
                .build();
    }

    public ArticuloResponse toResponse(Articulo domain) {
        if (domain == null) return null;
        return ArticuloResponse.builder()
                .articuloId(domain.getArticuloId())
                .negocioId(domain.getNegocioId())
                .descripcion(domain.getDescripcion())
                .leyendaVoucher(domain.getLeyendaVoucher())
                .precioVentaSinIva(domain.getPrecioVentaSinIva())
                .precioVentaConIva(domain.getPrecioVentaConIva())
                .numeroCuentaVentas(domain.getNumeroCuentaVentas())
                .numeroCuentaCompras(domain.getNumeroCuentaCompras())
                .numeroCuentaGastos(domain.getNumeroCuentaGastos())
                .centroStockId(domain.getCentroStockId())
                .rubroId(domain.getRubroId())
                .subRubroId(domain.getSubRubroId())
                .precioCompra(domain.getPrecioCompra())
                .iva105(domain.getIva105())
                .precioCompraNeto(domain.getPrecioCompraNeto())
                .exento(domain.getExento())
                .stockMinimo(domain.getStockMinimo())
                .stockOptimo(domain.getStockOptimo())
                .bloqueoCompras(domain.getBloqueoCompras())
                .bloqueoStock(domain.getBloqueoStock())
                .bloqueoVentas(domain.getBloqueoVentas())
                .unidadMedidaId(domain.getUnidadMedidaId())
                .conDecimales(domain.getConDecimales())
                .ventas(domain.getVentas())
                .compras(domain.getCompras())
                .unidadMedida(domain.getUnidadMedida())
                .conversionId(domain.getConversionId())
                .ventaSinStock(domain.getVentaSinStock())
                .controlaStock(domain.getControlaStock())
                .asientoCostos(domain.getAsientoCostos())
                .mascaraBalanza(domain.getMascaraBalanza())
                .habilitaIngreso(domain.getHabilitaIngreso())
                .comision(domain.getComision())
                .prestadorId(domain.getPrestadorId())
                .autoNumericoId(domain.getAutoNumericoId())
                .cuentaVentas(cuentaDtoMapper.toResponse(domain.getCuentaVentas()))
                .build();
    }

    public ArticuloResponseForInvoiceData toResponseForInvoiceData(Articulo domain) {
        if (domain == null) return null;
        return ArticuloResponseForInvoiceData.builder()
                .descripcion(domain.getDescripcion())
                .build();
    }
}
