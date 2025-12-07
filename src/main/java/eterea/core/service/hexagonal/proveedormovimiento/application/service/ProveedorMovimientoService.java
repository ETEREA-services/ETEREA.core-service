package eterea.core.service.hexagonal.proveedormovimiento.application.service;

import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.repository.ProveedorMovimientoJpaRepository;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.web.dto.ProveedorMovimientoNetoAjusteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProveedorMovimientoService {

    private final ProveedorMovimientoJpaRepository repository;

    public List<ProveedorMovimientoEntity> findAllByProveedorId(Long proveedorId) {
        return repository.findAllByProveedorIdOrderByFechaComprobante(proveedorId);
    }

    public List<ProveedorMovimientoEntity> findAllByRegimenInformacionCompras(OffsetDateTime desde,
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

    public ProveedorMovimientoEntity ajusteNetoInformacionArca(ProveedorMovimientoNetoAjusteRequest proveedorMovimientoNetoAjusteRequest) {
        return repository.findByProveedorMovimientoId(proveedorMovimientoNetoAjusteRequest.getProveedorMovimientoId())
                .map(movimiento -> {
                    movimiento.setNeto(proveedorMovimientoNetoAjusteRequest.getNetoAjustado());
                    movimiento.setMontoIva(proveedorMovimientoNetoAjusteRequest.getMontoIva21Ajustado());
                    movimiento.setMontoIva105(proveedorMovimientoNetoAjusteRequest.getMontoIva105Ajustado());
                    movimiento.setMontoIva27(proveedorMovimientoNetoAjusteRequest.getMontoIva27Ajustado());
                    return repository.save(movimiento);
                })
                .orElse(null);
    }

}
