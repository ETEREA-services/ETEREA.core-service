package eterea.core.service.hexagonal.proveedormovimiento.application.service;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in.GetProveedorMovimientosByProveedorIdUseCase;
import eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in.GetProveedorMovimientosByRegimenInformacionComprasUseCase;
import eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in.GetResumenIvaComprasMensualUseCase;
import eterea.core.service.hexagonal.proveedormovimiento.domain.ports.in.UpdateProveedorMovimientoNetoAjusteUseCase;
import eterea.core.service.hexagonal.proveedormovimiento.domain.ports.out.ProveedorMovimientoRepository;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ProveedorMovimientoNetoAjusteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorMovimientoService implements
        GetProveedorMovimientosByProveedorIdUseCase,
        GetProveedorMovimientosByRegimenInformacionComprasUseCase,
        UpdateProveedorMovimientoNetoAjusteUseCase,
        GetResumenIvaComprasMensualUseCase {

    private final ProveedorMovimientoRepository repository;

    @Override
    public List<ProveedorMovimiento> getProveedorMovimientosByProveedorId(Long proveedorId) {
        return repository.findAllByProveedorId(proveedorId);
    }

    @Override
    public List<ProveedorMovimiento> getProveedorMovimientosByRegimenInformacionCompras(OffsetDateTime desde,
            OffsetDateTime hasta) {
        return repository
                .findAllByFechaContableBetweenAndComprobanteLibroIva(desde, hasta, (byte) 1,
                        Sort.by("fechaComprobante").ascending()
                                .and(Sort.by("prefijo").ascending().and(Sort.by("numeroComprobante"))))
                .stream()
                .filter(movimiento -> movimiento.getMontoIva()
                        .add(movimiento.getMontoIva105().add(movimiento.getMontoIva27()))
                        .compareTo(BigDecimal.ZERO) != 0)
                .toList();
    }

    @Override
    public ProveedorMovimiento updateProveedorMovimientoNetoAjuste(ProveedorMovimientoNetoAjusteRequest request) {
        return repository.findByProveedorMovimientoId(request.getProveedorMovimientoId())
                .map(movimiento -> {
                    movimiento.setNeto(request.getNetoAjustado());
                    movimiento.setMontoIva(request.getMontoIva21Ajustado());
                    movimiento.setMontoIva105(request.getMontoIva105Ajustado());
                    movimiento.setMontoIva27(request.getMontoIva27Ajustado());
                    return repository.save(movimiento);
                })
                .orElse(null);
    }

    @Override
    public ResumenIvaComprasMensual getResumenIvaComprasMensual(Integer anho, Integer mes) {
        return repository.findResumenByYearAndMonth(anho, mes);
    }

}
