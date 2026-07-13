package eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.stock.articulo.domain.model.Articulo;
import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.entity.ArticuloEntity;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.mapper.CuentaMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticuloMapper {

    private final CuentaMapper cuentaMapper;

    public ArticuloMapper(CuentaMapper cuentaMapper) {
        this.cuentaMapper = cuentaMapper;
    }

    public Articulo toDomain(ArticuloEntity entity) {
        if (entity == null) return null;
        return Articulo.builder()
                .articuloId(entity.getArticuloId())
                .negocioId(entity.getNegocioId())
                .descripcion(entity.getDescripcion())
                .leyendaVoucher(entity.getLeyendaVoucher())
                .precioVentaSinIva(entity.getPrecioVentaSinIva())
                .precioVentaConIva(entity.getPrecioVentaConIva())
                .numeroCuentaVentas(entity.getNumeroCuentaVentas())
                .numeroCuentaCompras(entity.getNumeroCuentaCompras())
                .numeroCuentaGastos(entity.getNumeroCuentaGastos())
                .centroStockId(entity.getCentroStockId())
                .rubroId(entity.getRubroId())
                .subRubroId(entity.getSubRubroId())
                .precioCompra(entity.getPrecioCompra())
                .iva105(entity.getIva105())
                .precioCompraNeto(entity.getPrecioCompraNeto())
                .exento(entity.getExento())
                .stockMinimo(entity.getStockMinimo())
                .stockOptimo(entity.getStockOptimo())
                .bloqueoCompras(entity.getBloqueoCompras())
                .bloqueoStock(entity.getBloqueoStock())
                .bloqueoVentas(entity.getBloqueoVentas())
                .unidadMedidaId(entity.getUnidadMedidaId())
                .conDecimales(entity.getConDecimales())
                .ventas(entity.getVentas())
                .compras(entity.getCompras())
                .unidadMedida(entity.getUnidadMedida())
                .conversionId(entity.getConversionId())
                .ventaSinStock(entity.getVentaSinStock())
                .controlaStock(entity.getControlaStock())
                .asientoCostos(entity.getAsientoCostos())
                .mascaraBalanza(entity.getMascaraBalanza())
                .habilitaIngreso(entity.getHabilitaIngreso())
                .comision(entity.getComision())
                .prestadorId(entity.getPrestadorId())
                .autoNumericoId(entity.getAutoNumericoId())
                .cuentaVentas(cuentaMapper.toDomain(entity.getCuentaVentas()))
                .build();
    }

    public ArticuloEntity toEntity(Articulo domain) {
        if (domain == null) return null;
        return ArticuloEntity.builder()
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
                .build();
    }
}
