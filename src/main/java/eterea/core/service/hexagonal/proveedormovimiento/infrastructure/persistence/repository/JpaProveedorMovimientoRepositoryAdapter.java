package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ProveedorMovimiento;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensual;
import eterea.core.service.hexagonal.proveedormovimiento.domain.model.ResumenIvaComprasMensualPosicion;
import eterea.core.service.hexagonal.proveedormovimiento.domain.ports.out.ProveedorMovimientoRepository;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto.ResumenIvaComprasMensualDto;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.mapper.ProveedorMovimientoMapper;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.mapper.ResumenIvaComprasMensualMapper;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.mapper.ResumenIvaComprasMensualPosicionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JpaProveedorMovimientoRepositoryAdapter implements ProveedorMovimientoRepository {

    private final JpaProveedorMovimientoRepository jpaRepository;
    private final ProveedorMovimientoMapper proveedorMovimientoMapper;
    private final ResumenIvaComprasMensualMapper resumenIvaComprasMensualMapper;
    private final ResumenIvaComprasMensualPosicionMapper resumenIvaComprasMensualPosicionMapper;

    @Override
    public List<ProveedorMovimiento> findAllByProveedorId(Long proveedorId) {
        return jpaRepository.findAllByProveedorIdOrderByFechaComprobante(proveedorId)
                .stream()
                .map(proveedorMovimientoMapper::toDomain)
                .toList();
    }

    @Override
    public List<ProveedorMovimiento> findAllByFechaContableBetweenAndComprobanteLibroIva(OffsetDateTime desde, OffsetDateTime hasta, Byte libroIva, Sort sort) {
        return jpaRepository.findAllByFechaContableBetweenAndComprobanteLibroIva(desde, hasta, libroIva, sort)
                .stream()
                .map(proveedorMovimientoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ProveedorMovimiento> findByProveedorMovimientoId(Long proveedorMovimientoId) {
        return jpaRepository.findByProveedorMovimientoId(proveedorMovimientoId)
                .map(proveedorMovimientoMapper::toDomain);
    }

    @Override
    public ProveedorMovimiento save(ProveedorMovimiento proveedorMovimiento) {
        ProveedorMovimientoEntity entity = proveedorMovimientoMapper.toEntity(proveedorMovimiento);
        return proveedorMovimientoMapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public ResumenIvaComprasMensual findResumenByYearAndMonth(Integer anho, Integer mes) {
        return resumenIvaComprasMensualMapper.toDomain(jpaRepository.findResumenByYearAndMonth(anho, mes));
    }

    @Override
    public List<ResumenIvaComprasMensualPosicion> findAllResumenPosicionByYearAndMonth(Integer anho, Integer mes) {
        return jpaRepository.findAllResumenPosicionByYearAndMonth(anho, mes).stream()
                .map(resumenIvaComprasMensualPosicionMapper::toDomain)
                .toList();
    }

}
