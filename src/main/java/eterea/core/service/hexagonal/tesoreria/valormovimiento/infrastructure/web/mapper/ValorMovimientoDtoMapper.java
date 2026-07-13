package eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.mapper;

import eterea.core.service.hexagonal.contable.cuenta.infrastructure.web.mapper.CuentaDtoMapper;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.persistence.entity.ValorMovimientoEntity;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.infrastructure.web.dto.ValorMovimientoResponse;
import eterea.core.service.hexagonal.ventas.clientemovimiento.infrastructure.web.mapper.ValorDtoMapper;
import eterea.core.service.model.dto.ValorMovimientoDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ValorMovimientoDtoMapper implements Function<ValorMovimientoEntity, ValorMovimientoDto> {

    private final CuentaDtoMapper cuentaDtoMapper;
    private final ValorDtoMapper valorDtoMapper;

    public ValorMovimientoDtoMapper(CuentaDtoMapper cuentaDtoMapper, ValorDtoMapper valorDtoMapper) {
        this.cuentaDtoMapper = cuentaDtoMapper;
        this.valorDtoMapper = valorDtoMapper;
    }

    @Override
    public ValorMovimientoDto apply(ValorMovimientoEntity valorMovimiento) {
        return new ValorMovimientoDto(
                valorMovimiento.getValorMovimientoId(),
                valorMovimiento.getCierreCajaId(),
                valorMovimiento.getNegocioId(),
                valorMovimiento.getValor() != null ? valorMovimiento.getValor().getConcepto() : null,
                valorMovimiento.getFechaContable(),
                valorMovimiento.getImporte(),
                valorMovimiento.getClienteMovimientoId(),
                valorMovimiento.getClienteId(),
                valorMovimiento.getNumeroCuenta(),
                valorMovimiento.getCreated(),
                valorMovimiento.getUpdated());
    }

    public ValorMovimientoResponse toResponse(ValorMovimiento domain) {
        if (domain == null) {
            return null;
        }
        return ValorMovimientoResponse.builder()
                .valorMovimientoId(domain.getValorMovimientoId())
                .negocioId(domain.getNegocioId())
                .valorId(domain.getValorId())
                .proveedorId(domain.getProveedorId())
                .clienteId(domain.getClienteId())
                .fechaEmision(domain.getFechaEmision())
                .fechaVencimiento(domain.getFechaVencimiento())
                .comprobanteId(domain.getComprobanteId())
                .numeroComprobante(domain.getNumeroComprobante())
                .importe(domain.getImporte())
                .numeroCuenta(domain.getNumeroCuenta())
                .fechaContable(domain.getFechaContable())
                .ordenContable(domain.getOrdenContable())
                .proveedorMovimientoId(domain.getProveedorMovimientoId())
                .clienteMovimientoId(domain.getClienteMovimientoId())
                .titular(domain.getTitular())
                .banco(domain.getBanco())
                .receptor(domain.getReceptor())
                .estadoId(domain.getEstadoId())
                .fechaEntrega(domain.getFechaEntrega())
                .tanda(domain.getTanda())
                .tandaIndex(domain.getTandaIndex())
                .cierreCajaId(domain.getCierreCajaId())
                .nivel(domain.getNivel())
                .observaciones(domain.getObservaciones())
                .trackUuid(domain.getTrackUuid())
                .valor(valorDtoMapper.toResponse(domain.getValor()))
                .cuenta(cuentaDtoMapper.toResponse(domain.getCuenta()))
                .build();
    }

}
