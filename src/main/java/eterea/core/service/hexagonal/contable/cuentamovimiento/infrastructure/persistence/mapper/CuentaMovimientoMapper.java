package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.comprobante.infrastructure.persistence.mapper.ComprobanteMapper;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.persistence.mapper.CuentaMapper;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.persistence.entity.CuentaMovimientoEntity;
import eterea.core.service.hexagonal.negocio.infrastructure.persistence.mapper.NegocioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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
        entity.setOrden(domain.getOrden() != null ? domain.getOrden() : 0);
        entity.setItem(domain.getItem() != null ? domain.getItem() : 0);
        entity.setDebita(domain.getDebita() != null ? domain.getDebita() : 0);
        entity.setNegocioId(domain.getNegocioId() != null ? domain.getNegocioId() : 0);
        entity.setNumeroCuenta(domain.getNumeroCuenta() != null ? domain.getNumeroCuenta() : 0L);
        entity.setComprobanteId(domain.getComprobanteId() != null ? domain.getComprobanteId() : 0);
        entity.setConcepto(domain.getConcepto() != null ? domain.getConcepto() : "");
        entity.setImporte(domain.getImporte() != null ? domain.getImporte() : BigDecimal.ZERO);
        entity.setSubrubroId(domain.getSubrubroId() != null ? domain.getSubrubroId() : 0L);
        entity.setProveedorId(domain.getProveedorId() != null ? domain.getProveedorId() : 0L);
        entity.setClienteId(domain.getClienteId() != null ? domain.getClienteId() : 0L);
        entity.setLegajoId(domain.getLegajoId());
        entity.setCierreCajaId(domain.getCierreCajaId() != null ? domain.getCierreCajaId() : 0L);
        entity.setNivel(domain.getNivel() != null ? domain.getNivel() : 0);
        entity.setFirma(domain.getFirma() != null ? domain.getFirma() : 0L);
        entity.setTipoAsientoId(domain.getTipoAsientoId() != null ? domain.getTipoAsientoId() : 0);
        entity.setArticuloMovimientoId(domain.getArticuloMovimientoId() != null ? domain.getArticuloMovimientoId() : 0L);
        entity.setEjercicioId(domain.getEjercicioId());
        entity.setInflacion(domain.getInflacion() != null ? domain.getInflacion() : 0);
        entity.setTrackUuid(domain.getTrackUuid());
        return entity;
    }

}
