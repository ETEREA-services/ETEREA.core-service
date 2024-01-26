package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.ValorMovimientoException;
import eterea.core.api.rest.kotlin.model.ValorMovimiento;
import eterea.core.api.rest.kotlin.repository.ValorMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValorMovimientoService {

    private final ValorMovimientoRepository repository;

    @Autowired
    public ValorMovimientoService(ValorMovimientoRepository repository) {
        this.repository = repository;
    }

    public ValorMovimiento add(ValorMovimiento valorMovimiento) {
        return repository.save(valorMovimiento);
    }

    public ValorMovimiento update(ValorMovimiento newValorMovimiento, Long valorMovimientoId) {
        return repository.findByValorMovimientoId(valorMovimientoId).map(valorMovimiento -> {
            valorMovimiento = new ValorMovimiento.Builder()
                    .valorMovimientoId(valorMovimientoId)
                    .negocioId(newValorMovimiento.getNegocioId())
                    .valorId(newValorMovimiento.getValorId())
                    .proveedorId(newValorMovimiento.getProveedorId())
                    .clienteId(newValorMovimiento.getClienteId())
                    .fechaEmision(newValorMovimiento.getFechaEmision())
                    .fechaVencimiento(newValorMovimiento.getFechaVencimiento())
                    .comprobanteId(newValorMovimiento.getComprobanteId())
                    .numeroComprobante(newValorMovimiento.getNumeroComprobante())
                    .importe(newValorMovimiento.getImporte())
                    .numeroCuenta(newValorMovimiento.getNumeroCuenta())
                    .fechaContable(newValorMovimiento.getFechaContable())
                    .ordenContable(newValorMovimiento.getOrdenContable())
                    .proveedorMovimientoId(newValorMovimiento.getProveedorMovimientoId())
                    .clienteMovimientoId(newValorMovimiento.getClienteMovimientoId())
                    .titular(newValorMovimiento.getTitular())
                    .banco(newValorMovimiento.getBanco())
                    .receptor(newValorMovimiento.getReceptor())
                    .estadoId(newValorMovimiento.getEstadoId())
                    .fechaEntrega(newValorMovimiento.getFechaEntrega())
                    .tanda(newValorMovimiento.getTanda())
                    .tandaIndex(newValorMovimiento.getTandaIndex())
                    .cierreCajaId(newValorMovimiento.getCierreCajaId())
                    .nivel(newValorMovimiento.getNivel())
                    .observaciones(newValorMovimiento.getObservaciones())
                    .build();
            return repository.save(valorMovimiento);
        }).orElseThrow(() -> new ValorMovimientoException(valorMovimientoId));
    }
}
