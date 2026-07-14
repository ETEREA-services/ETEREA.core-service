package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.mapper.CuentaMapper;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.entity.ValorMovimientoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class ValorMovimientoMapper {

    private final CuentaMapper cuentaMapper;

    public ValorMovimiento toDomain(ValorMovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return ValorMovimiento.builder()
                .valorMovimientoId(entity.getValorMovimientoId())
                .negocioId(entity.getNegocioId())
                .valorId(entity.getValorId())
                .proveedorId(entity.getProveedorId())
                .clienteId(entity.getClienteId())
                .fechaEmision(entity.getFechaEmision())
                .fechaVencimiento(entity.getFechaVencimiento())
                .comprobanteId(entity.getComprobanteId())
                .numeroComprobante(entity.getNumeroComprobante())
                .importe(entity.getImporte())
                .numeroCuenta(entity.getNumeroCuenta())
                .fechaContable(entity.getFechaContable())
                .ordenContable(entity.getOrdenContable())
                .proveedorMovimientoId(entity.getProveedorMovimientoId())
                .clienteMovimientoId(entity.getClienteMovimientoId())
                .titular(entity.getTitular())
                .banco(entity.getBanco())
                .receptor(entity.getReceptor())
                .estadoId(entity.getEstadoId())
                .fechaEntrega(entity.getFechaEntrega())
                .tanda(entity.getTanda())
                .tandaIndex(entity.getTandaIndex())
                .cierreCajaId(entity.getCierreCajaId())
                .nivel(entity.getNivel())
                .observaciones(entity.getObservaciones())
                .trackUuid(entity.getTrackUuid())
                .valor(entity.getValor())
                .cuenta(cuentaMapper.toDomain(entity.getCuenta()))
                .build();
    }

    public ValorMovimientoEntity toEntity(ValorMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return ValorMovimientoEntity.builder()
                .valorMovimientoId(domain.getValorMovimientoId())
                .negocioId(domain.getNegocioId())
                .valorId(domain.getValorId())
                .proveedorId(domain.getProveedorId())
                .clienteId(domain.getClienteId())
                .fechaEmision(domain.getFechaEmision())
                .fechaVencimiento(domain.getFechaVencimiento())
                .comprobanteId(domain.getComprobanteId())
                .numeroComprobante(domain.getNumeroComprobante())
                .importe(domain.getImporte() != null ? domain.getImporte() : BigDecimal.ZERO)
                .numeroCuenta(domain.getNumeroCuenta())
                .fechaContable(domain.getFechaContable())
                .ordenContable(domain.getOrdenContable() != null ? domain.getOrdenContable() : 0)
                .proveedorMovimientoId(domain.getProveedorMovimientoId())
                .clienteMovimientoId(domain.getClienteMovimientoId())
                .titular(domain.getTitular())
                .banco(domain.getBanco())
                .receptor(domain.getReceptor())
                .estadoId(domain.getEstadoId())
                .fechaEntrega(domain.getFechaEntrega())
                .tanda(domain.getTanda() != null ? domain.getTanda() : 0L)
                .tandaIndex(domain.getTandaIndex() != null ? domain.getTandaIndex() : 0L)
                .cierreCajaId(domain.getCierreCajaId())
                .nivel(domain.getNivel() != null ? domain.getNivel() : 0)
                .observaciones(domain.getObservaciones())
                .trackUuid(domain.getTrackUuid())
                .build();
    }

}
