package eterea.core.service.hexagonal.tesoreria.valormovimiento.application.usecases;

import eterea.core.service.hexagonal.tesoreria.valormovimiento.application.exception.ValorMovimientoException;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.model.ValorMovimiento;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.in.UpdateValorMovimientoUseCase;
import eterea.core.service.hexagonal.tesoreria.valormovimiento.domain.ports.out.ValorMovimientoRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateValorMovimientoUseCaseImpl implements UpdateValorMovimientoUseCase {

    private final ValorMovimientoRepository repository;

    public UpdateValorMovimientoUseCaseImpl(ValorMovimientoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ValorMovimiento update(ValorMovimiento newValorMovimiento, Long valorMovimientoId) {
        ValorMovimiento valorMovimiento = repository.findByValorMovimientoId(valorMovimientoId)
                .orElseThrow(() -> new ValorMovimientoException(valorMovimientoId));

        valorMovimiento.setNegocioId(newValorMovimiento.getNegocioId());
        valorMovimiento.setValorId(newValorMovimiento.getValorId());
        valorMovimiento.setProveedorId(newValorMovimiento.getProveedorId());
        valorMovimiento.setClienteId(newValorMovimiento.getClienteId());
        valorMovimiento.setFechaEmision(newValorMovimiento.getFechaEmision());
        valorMovimiento.setFechaVencimiento(newValorMovimiento.getFechaVencimiento());
        valorMovimiento.setComprobanteId(newValorMovimiento.getComprobanteId());
        valorMovimiento.setNumeroComprobante(newValorMovimiento.getNumeroComprobante());
        valorMovimiento.setImporte(newValorMovimiento.getImporte());
        valorMovimiento.setNumeroCuenta(newValorMovimiento.getNumeroCuenta());
        valorMovimiento.setFechaContable(newValorMovimiento.getFechaContable());
        valorMovimiento.setOrdenContable(newValorMovimiento.getOrdenContable());
        valorMovimiento.setProveedorMovimientoId(newValorMovimiento.getProveedorMovimientoId());
        valorMovimiento.setClienteMovimientoId(newValorMovimiento.getClienteMovimientoId());
        valorMovimiento.setTitular(newValorMovimiento.getTitular());
        valorMovimiento.setBanco(newValorMovimiento.getBanco());
        valorMovimiento.setReceptor(newValorMovimiento.getReceptor());
        valorMovimiento.setEstadoId(newValorMovimiento.getEstadoId());
        valorMovimiento.setFechaEntrega(newValorMovimiento.getFechaEntrega());
        valorMovimiento.setTanda(newValorMovimiento.getTanda());
        valorMovimiento.setTandaIndex(newValorMovimiento.getTandaIndex());
        valorMovimiento.setCierreCajaId(newValorMovimiento.getCierreCajaId());
        valorMovimiento.setNivel(newValorMovimiento.getNivel());
        valorMovimiento.setObservaciones(newValorMovimiento.getObservaciones());
        valorMovimiento.setTrackUuid(newValorMovimiento.getTrackUuid());

        return repository.save(valorMovimiento);
    }
}
