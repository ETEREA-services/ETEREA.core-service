/**
 *
 */
package eterea.core.service.repository;

import eterea.core.service.kotlin.model.Grupo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * @author daniel
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {

    List<Grupo> findAllByVentaInternet(Byte habilitado, Sort sort);

    @Query(value = "SELECT DISTINCT GrP_Gru_ID FROM " +
            "(SELECT Vou_ID FROM voucher vou WHERE vou.Vou_FechaIn = ?1) v" +
            "JOIN voucherproducto vp" +
            "ON v.Vou_ID = vp.Vou_ID" +
            "FETCH JOIN grupoproducto gp" +
            "ON vp.VPr_Prd_ID = gp.GrP_Prd_ID", nativeQuery = true)
    List<Integer> findAllByFecha(@Param("fecha") OffsetDateTime fecha);

    @Query("""
            SELECT DISTINCT g
            FROM Grupo g
                JOIN GrupoProducto gp
                    ON g.grupoId = gp.grupoId
            """)
    List<Grupo> findAllWithProductos();

    /*
     * 
     * SELECT g.*
     * FROM
     * grupo g
     * JOIN grupoproducto gp
     * ON g.Gru_ID = gp.GrP_Gru_ID
     * JOIN voucherproducto vp
     * ON vp.VPr_Prd_ID = gp.GrP_Prd_ID
     * JOIN voucher v
     * ON vp.VPr_Vou_ID = v.Vou_ID
     * WHERE
     * v.Vou_FechaIn = '2024-11-19' # Para la fecha seleccionada
     * GROUP BY
     * g.Gru_ID;
     */

    @Query("""
            SELECT g
            FROM
               Grupo g
               JOIN GrupoProducto gp
                   ON g.grupoId = gp.grupoId
               JOIN VoucherProducto vp
                   ON gp.productoId = vp.productoId
               JOIN Voucher v
                   ON vp.voucherId = v.voucherId
            WHERE
               v.fechaServicio = :fechaServicio
            GROUP BY
               g.grupoId
            """)
    List<Grupo> findAllByVoucherFechaServicio(@Param("fechaServicio") OffsetDateTime fechaServicio);

    /*
     * 
     * SELECT SUM(vp.VPr_Paxs * gp.GrP_Coeficiente) AS cantidad
     * FROM
     * voucher v
     * JOIN voucherproducto vp
     * ON vp.VPr_Vou_ID = v.Vou_ID
     * JOIN grupoproducto gp
     * ON gp.GrP_Prd_ID = vp.VPr_Prd_ID
     * WHERE
     * gp.GrP_Gru_ID = 23 # Por cada Grupo
     * AND
     * v.Vou_FechaIn = '2025-01-27'; # Para la fecha seleccionada
     */
    @Query("""
            SELECT SUM(vp.cantidadPaxs * gp.coeficiente)
            FROM
                Voucher v
                JOIN VoucherProducto vp
                    ON vp.voucherId = v.voucherId
                JOIN GrupoProducto gp
                    ON gp.productoId = vp.productoId
            WHERE
                gp.grupoId = :grupoId
                AND
                v.fechaServicio = :fechaServicio
            """)
    BigDecimal totalVentasByGrupoIdAndVoucherFechaServicio(@Param("grupoId") Integer grupoId,
            @Param("fechaServicio") OffsetDateTime fechaServicio);

    
    @Query("""
            SELECT SUM(vp.cantidadPaxs * gp.coeficiente)
            FROM
                Voucher v
                JOIN VoucherProducto vp
                    ON vp.voucherId = v.voucherId
                JOIN GrupoProducto gp
                    ON gp.productoId = vp.productoId
            WHERE
                gp.grupoId = :grupoId
                AND
                v.voucherId = :voucherId
            """)
    BigDecimal totalVentasByGrupoIdAndVoucherId(@Param("grupoId") Integer grupoId,
            @Param("voucherId") Integer voucherId);

}
