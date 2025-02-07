/**
 * 
 */
package eterea.core.service.repository;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.model.dto.programadia.ProgramaDiaVentasProveedorDto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	Optional<Proveedor> findByProveedorId(Integer proveedorId);

	/*
	 * 
	 * SELECT prov.*
	 * FROM
	 * grupo g
	 * JOIN grupoproducto gp
	 * ON g.Gru_ID = gp.GrP_Gru_ID
	 * JOIN voucherproducto vp
	 * ON vp.VPr_Prd_ID = gp.GrP_Prd_ID
	 * JOIN voucher v
	 * ON vp.VPr_Vou_ID = v.Vou_ID
	 * JOIN producto p
	 * ON p.Prd_ID = vp.VPr_Prd_ID
	 * JOIN proveedores prov
	 * ON prov.codigo = v.Vou_Pro_ID
	 * WHERE
	 * v.Vou_FechaIn = '2025-01-27' # Para la fecha seleccionada
	 * AND
	 * p.traslado = 1
	 * AND
	 * g.Gru_ID = 23 # Por cada Grupo
	 * GROUP BY
	 * prov.codigo;
	 */

	 @Query("""
	 SELECT prov
	 FROM
		 Grupo g
		 JOIN GrupoProducto gp
			 ON g.grupoId = gp.grupoId
		 JOIN VoucherProducto vp
			 ON vp.producto.productoId = gp.productoId
		 JOIN Voucher v
			 ON vp.voucherId = v.voucherId
		 JOIN Producto p
			 ON p.productoId = vp.producto.productoId
		 JOIN Proveedor prov
			 ON prov.proveedorId = v.proveedorId
	 WHERE
		 v.fechaServicio = :fechaServicio
		 AND
		 p.traslado = 1
		 AND
		 g.grupoId = :grupoId
	 GROUP BY
		 prov.proveedorId
	 """)
List<Proveedor> findAllByGrupoIdAndVoucherFechaServicio(@Param("grupoId") Integer grupoId,
	 @Param("fechaServicio") OffsetDateTime fechaServicio);

/*
* 
* SELECT SUM(vp.VPr_Paxs * gp.GrP_Coeficiente ) AS cantidad
* FROM
* grupoproducto gp
* JOIN voucherproducto vp
* ON vp.VPr_Prd_ID = gp.GrP_Prd_ID
* JOIN voucher v
* ON v.Vou_ID = vp.VPr_Vou_ID
* JOIN producto p
* ON p.Prd_ID = vp.VPr_Prd_ID
* JOIN proveedores prov
* ON prov.codigo = v.Vou_Pro_ID
* WHERE
* gp.GrP_Gru_ID = 23 # Por cada Grupo
* AND
* v.Vou_FechaIn = '2025-01-27' # Para la fecha seleccionada
* AND
* p.traslado = 1
* AND
* prov.codigo = 31; # Por cada Proveedor
*/
@Query("""
	 SELECT SUM(vp.cantidadPaxs * gp.coeficiente)
	 FROM
		 GrupoProducto gp
		 JOIN VoucherProducto vp
			 ON vp.producto.productoId = gp.productoId
		 JOIN Voucher v
			 ON v.voucherId = vp.voucherId
		 JOIN Producto p
			 ON p.productoId = vp.producto.productoId
		 JOIN Proveedor prov
			 ON prov.proveedorId = v.proveedorId
	 WHERE
		 gp.grupoId = :grupoId
		 AND
		 v.fechaServicio = :fechaServicio
		 AND
		 p.traslado = 1
		 AND
		 prov.proveedorId = :proveedorId
	 """)
BigDecimal totalVentasByProveedorIdAndGrupoIdAndVoucherFechaServicio(@Param("proveedorId") Long proveedorId,
	 @Param("grupoId") Integer grupoId, @Param("fechaServicio") OffsetDateTime fechaServicio);

	 @Query("""
			SELECT DISTINCT
            new eterea.core.service.model.dto.programadia.ProgramaDiaVentasProveedorDto(
                prov.razonSocial,
                p.productoId,
                SUM(vp.cantidadPaxs * gp.coeficiente)
            )
        FROM
            GrupoProducto gp
            JOIN VoucherProducto vp ON vp.producto.productoId = gp.productoId
            JOIN Voucher v ON v.voucherId = vp.voucherId
            JOIN Producto p ON p.productoId = vp.producto.productoId
            JOIN Proveedor prov ON prov.proveedorId = v.proveedorId
        WHERE
            gp.grupoId = :grupoId
            AND v.fechaServicio = :fechaServicio
            AND p.traslado = 1
            AND prov.proveedorId = :proveedorId
        GROUP BY
            prov.razonSocial,
            p.productoId
			""")
	List<ProgramaDiaVentasProveedorDto> findVentasPorGrupoPorProveedor(@Param("grupoId") Integer grupoId,
																				 @Param("fechaServicio") OffsetDateTime fechaServicio, @Param("proveedorId") Long proveedorId);

}
