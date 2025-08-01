package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ValorMovimientoException;
import eterea.core.service.kotlin.model.ValorMovimiento;
import eterea.core.service.kotlin.repository.ValorMovimientoRepository;
import eterea.core.service.model.dto.ValorMovimientoDto;
import eterea.core.service.model.dto.ValorMovimientoDtoMapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValorMovimientoService {

    private final ValorMovimientoRepository repository;
    private final ValorMovimientoDtoMapper valorMovimientoDtoMapper;

    public ValorMovimientoService(ValorMovimientoRepository repository,
            ValorMovimientoDtoMapper valorMovimientoDtoMapper) {
        this.repository = repository;
        this.valorMovimientoDtoMapper = valorMovimientoDtoMapper;
    }

    public List<ValorMovimiento> findAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        return repository.findAllByFechaContableAndOrdenContable(fechaContable, ordenContable);
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

    public List<ValorMovimientoDto> findAllMovimientos(LocalDate desde,
            LocalDate hasta,
            boolean cierreCajaOnly,
            boolean ingresosOnly) {
        return repository
                .findAllByFechaContableBetween(
                        OffsetDateTime.of(desde.atTime(LocalTime.MIN), ZoneOffset.UTC),
                        OffsetDateTime.of(hasta.atTime(LocalTime.MIN), ZoneOffset.UTC), cierreCajaOnly,
                        ingresosOnly)
                .stream()
                .map(valorMovimientoDtoMapper)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteAllByContable(OffsetDateTime fechaContable, Integer ordenContable) {
        log.debug("Intentando eliminar valores para fecha={} orden={}", fechaContable, ordenContable);
        repository.deleteAllByFechaContableAndOrdenContable(fechaContable, ordenContable);
    }

    public List<ValorMovimiento> saveAll(List<ValorMovimiento> valorMovimientos) {
        return repository.saveAll(valorMovimientos);
    }

    public List<ValorMovimiento> findAllByFechaContableBetweenAndComprobanteIdAndClienteMovimientoIdIn(
            OffsetDateTime fechaDesde,
            OffsetDateTime fechaHasta,
            Integer comprobanteId,
            List<Long> clienteMovimientoIds) {
        return repository.findAllByFechaContableBetweenAndComprobanteIdAndClienteMovimientoIdIn(fechaDesde, fechaHasta, comprobanteId, clienteMovimientoIds);
    }
}
