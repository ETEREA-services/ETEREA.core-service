/**
 * 
 */
package eterea.core.service.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto> findAllByVentaInternet(Byte habilitado, Sort sort);

	Optional<Producto> findByProductoId(Integer productoId);

	@Query("""
					SELECT
						p
					FROM Producto p
						JOIN GrupoProducto gp
							ON p.productoId = gp.productoId
					WHERE
						gp.grupoId = :grupoId
			""")
	List<Producto> findAllByGrupoId(Integer grupoId);

	@Query("""
					SELECT
						p
					FROM Producto p
						JOIN VoucherProducto vp
							ON p.productoId = vp.productoId
					WHERE
						vp.voucherId = :voucherId
			""")
	List<Producto> findAllByVoucherId(Long voucherId);

	List<Producto> findAllByProductoIdIn(List<Integer> productoIds);

	@Query("""
			SELECT DISTINCT p FROM Producto p
			   WHERE p.productoId IN (
			       SELECT pa.productoId
			       FROM ProductoArticulo pa
			       WHERE pa.articuloId IN :articuloIds
			       GROUP BY pa.productoId
			       HAVING COUNT(pa.articuloId) = :totalArticulos
			       AND COUNT(DISTINCT pa.articuloId) = :totalArticulos
			   )
			""")
	List<Producto> findByExactArticuloIds(List<String> articuloIds, int totalArticulos);
}
