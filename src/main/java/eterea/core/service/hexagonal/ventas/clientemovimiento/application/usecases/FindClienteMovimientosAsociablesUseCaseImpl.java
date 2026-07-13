package eterea.core.service.hexagonal.ventas.clientemovimiento.application.usecases;

import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.model.ClienteMovimiento;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.in.FindClienteMovimientosAsociablesUseCase;
import eterea.core.service.hexagonal.ventas.clientemovimiento.domain.ports.out.ClienteMovimientoRepository;
import eterea.core.service.hexagonal.comprobante.application.service.ComprobanteService;
import eterea.core.service.hexagonal.comprobante.domain.model.Comprobante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FindClienteMovimientosAsociablesUseCaseImpl implements FindClienteMovimientosAsociablesUseCase {

    private final ClienteMovimientoRepository repository;
    private final ComprobanteService comprobanteService;

    public FindClienteMovimientosAsociablesUseCaseImpl(ClienteMovimientoRepository repository,
                                                       ComprobanteService comprobanteService) {
        this.repository = repository;
        this.comprobanteService = comprobanteService;
    }

    @Override
    public List<ClienteMovimiento> findTop200Asociables(Long clienteId, Integer comprobanteId) {
        var comprobante = comprobanteService.findByComprobanteId(comprobanteId);
        List<Comprobante> comprobantes = (comprobante.getAsociado() == (byte) 1 && comprobante.getDebita() == (byte) 1)
            ? comprobanteService.findAllAsociables()
            : comprobanteService.findAllAsociables(comprobante.getDebita());
        List<Integer> comprobanteIds = comprobantes.stream()
                .map(Comprobante::getComprobanteId).collect(Collectors.toList());
        return repository.findTop200ByClienteIdAndComprobanteIdInOrderByClienteMovimientoIdDesc(clienteId, comprobanteIds);
    }
}
