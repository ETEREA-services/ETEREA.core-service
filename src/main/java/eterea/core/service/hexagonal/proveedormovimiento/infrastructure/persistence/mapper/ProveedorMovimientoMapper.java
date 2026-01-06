package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.mapper;

import eterea.core.service.hexagonal.negocio.infrastructure.persistence.mapper.NegocioMapper;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto.ResumenIvaComprasMensualDto;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProveedorMovimientoMapper {

    private final NegocioMapper negocioMapper;

    public ProveedorMovimiento toDomain(ProveedorMovimientoEntity entity) {
        if (entity == null) {
            return null;
        }
        return ProveedorMovimiento.builder()
                .proveedorMovimientoId(entity.getProveedorMovimientoId())
                .empresaId(entity.getEmpresaId())
                .negocioId(entity.getNegocioId())
                .proveedorId(entity.getProveedorId())
                .comprobanteId(entity.getComprobanteId())
                .fechaComprobante(entity.getFechaComprobante())
                .fechaVencimiento(entity.getFechaVencimiento())
                .prefijo(entity.getPrefijo())
                .numeroComprobante(entity.getNumeroComprobante())
                .importe(entity.getImporte())
                .cancelado(entity.getCancelado())
                .aplicado(entity.getAplicado())
                .neto(entity.getNeto())
                .netoCancelado(entity.getNetoCancelado())
                .montoIva(entity.getMontoIva())
                .montoIva27(entity.getMontoIva27())
                .montoIva105(entity.getMontoIva105())
                .percepcionIva(entity.getPercepcionIva())
                .percepcionIngresosBrutos(entity.getPercepcionIngresosBrutos())
                .gastosNoGravados(entity.getGastosNoGravados())
                .ajustes(entity.getAjustes())
                .fechaContable(entity.getFechaContable())
                .ordenContable(entity.getOrdenContable())
                .montoFacturadoC(entity.getMontoFacturadoC())
                .montoSujetoRetencionesIIBB(entity.getMontoSujetoRetencionesIIBB())
                .montoRetencionesIIBB(entity.getMontoRetencionesIIBB())
                .codigoRetencionesIIBB(entity.getCodigoRetencionesIIBB())
                .numeroComprobanteRetencionesIIBB(entity.getNumeroComprobanteRetencionesIIBB())
                .concepto(entity.getConcepto())
                .cierreCajaId(entity.getCierreCajaId())
                .nivel(entity.getNivel())
                .negocioIdPaga(entity.getNegocioIdPaga())
                .concursada(entity.getConcursada())
                .importeConcursado(entity.getImporteConcursado())
                .fechaContableConcurso(entity.getFechaContableConcurso())
                .ordenContableConcurso(entity.getOrdenContableConcurso())
                .marca(entity.getMarca())
                .orden(entity.getOrden())
                .transferida(entity.getTransferida())
                .comprobante(entity.getComprobante())
                .proveedor(entity.getProveedor())
                .negocio(negocioMapper.toDomainModel(entity.getNegocio()))
                .build();
    }

    public ProveedorMovimientoEntity toEntity(ProveedorMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return ProveedorMovimientoEntity.builder()
                .proveedorMovimientoId(domain.getProveedorMovimientoId())
                .empresaId(domain.getEmpresaId())
                .negocioId(domain.getNegocioId())
                .proveedorId(domain.getProveedorId())
                .comprobanteId(domain.getComprobanteId())
                .fechaComprobante(domain.getFechaComprobante())
                .fechaVencimiento(domain.getFechaVencimiento())
                .prefijo(domain.getPrefijo())
                .numeroComprobante(domain.getNumeroComprobante())
                .importe(domain.getImporte())
                .cancelado(domain.getCancelado())
                .aplicado(domain.getAplicado())
                .neto(domain.getNeto())
                .netoCancelado(domain.getNetoCancelado())
                .montoIva(domain.getMontoIva())
                .montoIva27(domain.getMontoIva27())
                .montoIva105(domain.getMontoIva105())
                .percepcionIva(domain.getPercepcionIva())
                .percepcionIngresosBrutos(domain.getPercepcionIngresosBrutos())
                .gastosNoGravados(domain.getGastosNoGravados())
                .ajustes(domain.getAjustes())
                .fechaContable(domain.getFechaContable())
                .ordenContable(domain.getOrdenContable())
                .montoFacturadoC(domain.getMontoFacturadoC())
                .montoSujetoRetencionesIIBB(domain.getMontoSujetoRetencionesIIBB())
                .montoRetencionesIIBB(domain.getMontoRetencionesIIBB())
                .codigoRetencionesIIBB(domain.getCodigoRetencionesIIBB())
                .numeroComprobanteRetencionesIIBB(domain.getNumeroComprobanteRetencionesIIBB())
                .concepto(domain.getConcepto())
                .cierreCajaId(domain.getCierreCajaId())
                .nivel(domain.getNivel())
                .negocioIdPaga(domain.getNegocioIdPaga())
                .concursada(domain.getConcursada())
                .importeConcursado(domain.getImporteConcursado())
                .fechaContableConcurso(domain.getFechaContableConcurso())
                .ordenContableConcurso(domain.getOrdenContableConcurso())
                .marca(domain.getMarca())
                .orden(domain.getOrden())
                .transferida(domain.getTransferida())
                // Relationships are not usually persisted back from domain unless needed.
                // For updates, we usually only update the fields of the entity itself.
                // Since this mapper is used for save(), we need to map the ID fields which are already there.
                // We should NOT map the relations back to Entity if they are read-only (@JoinColumn(insertable=false...))
                .build();
    }

}
