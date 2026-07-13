package eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.comprobante.infrastructure.web.mapper.ComprobanteDtoMapper;
import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.mapper.CuentaDtoMapper;
import eterea.core.service.hexagonal.contable.cuentamovimiento.domain.model.CuentaMovimiento;
import eterea.core.service.hexagonal.contable.cuentamovimiento.infrastructure.web.dto.CuentaMovimientoResponse;
import eterea.core.service.hexagonal.negocio.infrastructure.web.mapper.NegocioDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CuentaMovimientoDtoMapper {

    private final CuentaDtoMapper cuentaDtoMapper;
    private final ComprobanteDtoMapper comprobanteDtoMapper;
    private final NegocioDtoMapper negocioDtoMapper;

    public CuentaMovimientoDtoMapper(
            CuentaDtoMapper cuentaDtoMapper,
            ComprobanteDtoMapper comprobanteDtoMapper,
            NegocioDtoMapper negocioDtoMapper) {
        this.cuentaDtoMapper = cuentaDtoMapper;
        this.comprobanteDtoMapper = comprobanteDtoMapper;
        this.negocioDtoMapper = negocioDtoMapper;
    }

    public CuentaMovimientoResponse toResponse(CuentaMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return CuentaMovimientoResponse.builder()
                .cuentaMovimientoId(domain.getCuentaMovimientoId())
                .fecha(domain.getFecha())
                .orden(domain.getOrden())
                .item(domain.getItem())
                .debita(domain.getDebita())
                .negocioId(domain.getNegocioId())
                .numeroCuenta(domain.getNumeroCuenta())
                .comprobanteId(domain.getComprobanteId())
                .concepto(domain.getConcepto())
                .importe(domain.getImporte())
                .subrubroId(domain.getSubrubroId())
                .proveedorId(domain.getProveedorId())
                .clienteId(domain.getClienteId())
                .legajoId(domain.getLegajoId())
                .cierreCajaId(domain.getCierreCajaId())
                .nivel(domain.getNivel())
                .firma(domain.getFirma())
                .tipoAsientoId(domain.getTipoAsientoId())
                .articuloMovimientoId(domain.getArticuloMovimientoId())
                .ejercicioId(domain.getEjercicioId())
                .inflacion(domain.getInflacion())
                .trackUuid(domain.getTrackUuid())
                .cuenta(cuentaDtoMapper.toResponse(domain.getCuenta()))
                .comprobante(comprobanteDtoMapper.toResponse(domain.getComprobante()))
                .negocio(negocioDtoMapper.toResponse(domain.getNegocio()))
                .build();
    }

}
