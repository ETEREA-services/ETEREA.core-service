package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper.ComprobanteMapper;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.mapper.CuentaMapper;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.entity.CuentaMovimientoEntity;
import eterea.core.service.hexagonal.negocio.infrastructure.persistence.mapper.NegocioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CuentaMovimientoMapper {

    private final CuentaMapper cuentaMapper;
    private final ComprobanteMapper comprobanteMapper;
    private final NegocioMapper negocioMapper;

    public CuentaMovimiento toDomain(CuentaMovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return CuentaMovimiento.builder()
                .cuentaMovimientoId(entity.getCuentaMovimientoId())
                .fecha(entity.getFecha())
                .orden(entity.getOrden())
                .item(entity.getItem())
                .debita(entity.getDebita())
                .negocioId(entity.getNegocioId())
                .numeroCuenta(entity.getNumeroCuenta())
                .comprobanteId(entity.getComprobanteId())
                .concepto(entity.getConcepto())
                .importe(entity.getImporte())
                .subrubroId(entity.getSubrubroId())
                .proveedorId(entity.getProveedorId())
                .clienteId(entity.getClienteId())
                .legajoId(entity.getLegajoId())
                .cierreCajaId(entity.getCierreCajaId())
                .nivel(entity.getNivel())
                .firma(entity.getFirma())
                .tipoAsientoId(entity.getTipoAsientoId())
                .articuloMovimientoId(entity.getArticuloMovimientoId())
                .ejercicioId(entity.getEjercicioId())
                .inflacion(entity.getInflacion())
                .trackUuid(entity.getTrackUuid())
                .cuenta(cuentaMapper.toDomain(entity.getCuenta()))
                .comprobante(comprobanteMapper.toDomain(entity.getComprobante()))
                .negocio(negocioMapper.toDomainModel(entity.getNegocio()))
                .build();
    }

    public CuentaMovimientoEntity toEntity(CuentaMovimiento domain) {
        if (domain == null) {
            return null;
        }
        CuentaMovimientoEntity entity = new CuentaMovimientoEntity();
        entity.setCuentaMovimientoId(domain.getCuentaMovimientoId());
        entity.setFecha(domain.getFecha());
        entity.setOrden(domain.getOrden());
        entity.setItem(domain.getItem());
        entity.setDebita(domain.getDebita());
        entity.setNegocioId(domain.getNegocioId());
        entity.setNumeroCuenta(domain.getNumeroCuenta());
        entity.setComprobanteId(domain.getComprobanteId());
        entity.setConcepto(domain.getConcepto());
        entity.setImporte(domain.getImporte());
        entity.setSubrubroId(domain.getSubrubroId());
        entity.setProveedorId(domain.getProveedorId());
        entity.setClienteId(domain.getClienteId());
        entity.setLegajoId(domain.getLegajoId());
        entity.setCierreCajaId(domain.getCierreCajaId());
        entity.setNivel(domain.getNivel());
        entity.setFirma(domain.getFirma());
        entity.setTipoAsientoId(domain.getTipoAsientoId());
        entity.setArticuloMovimientoId(domain.getArticuloMovimientoId());
        entity.setEjercicioId(domain.getEjercicioId());
        entity.setInflacion(domain.getInflacion());
        entity.setTrackUuid(domain.getTrackUuid());
        return entity;
    }

}
