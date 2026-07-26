package eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.reservas.reservaarticulo.domain.model.ReservaArticulo;
import eterea.core.service.hexagonal.reservas.reservaarticulo.infrastructure.persistence.entity.ReservaArticuloEntity;
import eterea.core.service.hexagonal.stock.articulo.infrastructure.persistence.mapper.ArticuloMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReservaArticuloMapper {

    private final ArticuloMapper articuloMapper;

    public ReservaArticulo toDomain(ReservaArticuloEntity entity) {
        if (entity == null) return null;
        return ReservaArticulo.builder()
                .reservaArticuloId(entity.getReservaArticuloId())
                .negocioId(entity.getNegocioId())
                .reservaId(entity.getReservaId())
                .voucherId(entity.getVoucherId())
                .articuloId(entity.getArticuloId())
                .cantidad(entity.getCantidad())
                .comision(entity.getComision())
                .precioUnitarioSinComision(entity.getPrecioUnitarioSinComision())
                .precioUnitario(entity.getPrecioUnitario())
                .precioUnitarioSinDescuento(entity.getPrecioUnitarioSinDescuento())
                .precioCompra(entity.getPrecioCompra())
                .observaciones(entity.getObservaciones())
                .trackUuid(entity.getTrackUuid())
                .articulo(articuloMapper.toDomain(entity.getArticulo()))
                .build();
    }

    public ReservaArticuloEntity toEntity(ReservaArticulo domain) {
        if (domain == null) return null;
        return ReservaArticuloEntity.builder()
                .reservaArticuloId(domain.getReservaArticuloId())
                .negocioId(domain.getNegocioId())
                .reservaId(domain.getReservaId())
                .voucherId(domain.getVoucherId())
                .articuloId(domain.getArticuloId())
                .cantidad(domain.getCantidad())
                .comision(domain.getComision())
                .precioUnitarioSinComision(domain.getPrecioUnitarioSinComision())
                .precioUnitario(domain.getPrecioUnitario())
                .precioUnitarioSinDescuento(domain.getPrecioUnitarioSinDescuento())
                .precioCompra(domain.getPrecioCompra())
                .observaciones(domain.getObservaciones())
                .trackUuid(domain.getTrackUuid())
                .build();
    }

}
