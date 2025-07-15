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

}
