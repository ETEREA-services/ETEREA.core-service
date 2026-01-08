package eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.repository;

import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto.ResumenIvaComprasMensualDto;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.dto.ResumenIvaComprasMensualPosicionDto;
import eterea.core.service.hexagonal.proveedormovimiento.infrastructure.persistence.entity.ProveedorMovimientoEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaProveedorMovimientoRepository extends JpaRepository<ProveedorMovimientoEntity, Integer> {

    List<ProveedorMovimientoEntity> findAllByProveedorIdOrderByFechaComprobante(Long proveedorId);
    List<ProveedorMovimientoEntity> findAllByFechaContableBetweenAndComprobanteLibroIva(
            OffsetDateTime desde,
            OffsetDateTime hasta,
            Byte libroIva,
            Sort sort
    );

    Optional<ProveedorMovimientoEntity> findByProveedorMovimientoId(Long proveedorMovimientoId);

    @Query(value = "SELECT " +
            "movprov.`MPr_Neg_ID` AS negocio_id, " +
            "YEAR(`movprov`.`FechaReg`) AS `anho`, " +
            "MONTH(`movprov`.`FechaReg`) AS `mes`, " +
            "SUM(`movprov`.`Neto`)  AS `neto`, " +
            "SUM(`movprov`.`MontoFactC`) AS facturado_c, " +
            "SUM(movprov.`GNG`) AS gastos_no_gravados, " +
            "SUM(`movprov`.`MontoIva`) AS `iva21`, " +
            "SUM(`movprov`.`MontoIva27`) AS `iva27`, " +
            "SUM(`movprov`.`MontoIva105`) AS `iva105`, " +
            "SUM(`movprov`.`PercIva`) AS `percepcion_iva`, " +
            "SUM(`movprov`.`PercIngBrutos`) AS `percepcion_ingresos_brutos`, " +
            "SUM(`movprov`.`Neto` + `movprov`.`MontoFactC` + `movprov`.`GNG` + `movprov`.`MontoIva`+ `movprov`.`MontoIva27` + `movprov`.`MontoIva105` + `movprov`.`PercIva` + `movprov`.`PercIngBrutos`) AS total_calculado, " +
            "SUM(`movprov`.`Importe`) AS `total` " +
            "FROM `movprov` " +
            "JOIN `tiposcomprob` " +
            "ON `movprov`.`CgoComprob` = `tiposcomprob`.`codigo` " +
            "WHERE (`tiposcomprob`.`LibroIva` <> 0) " +
            "AND YEAR(`movprov`.`FechaReg`) = :year " +
            "AND MONTH(`movprov`.`FechaReg`) = :month " +
            "GROUP BY `movprov`.`MPr_Neg_ID`,YEAR(`movprov`.`FechaReg`),MONTH(`movprov`.`FechaReg`)", nativeQuery = true)
    ResumenIvaComprasMensualDto findResumenByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "SELECT " +
            "movprov.`MPr_Neg_ID` AS negocio_id, " +
            "YEAR(`movprov`.`FechaReg`) AS `anho`, " +
            "MONTH(`movprov`.`FechaReg`) AS `mes`, " +
            "`proveedores`.`Posicion` AS `posicion`, " +
            "SUM(`movprov`.`Neto`)    AS `neto`, " +
            "SUM(`movprov`.`MontoFactC`) AS facturado_c, " +
            "SUM(`movprov`.`GNG`) AS `gastos_no_gravados`, " +
            "SUM(`movprov`.`MontoIva`) AS `iva21`, " +
            "SUM(`movprov`.`MontoIva27`) AS `iva27`, " +
            "SUM(`movprov`.`MontoIva105`) AS `iva105`, " +
            "SUM(`movprov`.`PercIva`) AS `percepcion_iva`, " +
            "SUM(`movprov`.`PercIngBrutos`) AS `percepcion_ingresos_brutos`, " +
            "SUM(`movprov`.`Importe`) AS `total` " +
            "FROM `movprov` " +
            "JOIN `proveedores` " +
            "ON `movprov`.`CgoProv` = `proveedores`.`codigo` " +
            "JOIN `tiposcomprob` " +
            "ON `movprov`.`CgoComprob` = `tiposcomprob`.`codigo` " +
            "WHERE `tiposcomprob`.`LibroIva` <> 0 " +
            "AND YEAR(movprov.`FechaReg`) = :year " +
            "AND MONTH(movprov.`FechaReg`) = :month " +
            "GROUP BY `movprov`.`MPr_Neg_ID`,YEAR(`movprov`.`FechaReg`),MONTH(`movprov`.`FechaReg`),`proveedores`.`Posicion`", nativeQuery = true)
    List<ResumenIvaComprasMensualPosicionDto> findAllResumenPosicionByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
}
