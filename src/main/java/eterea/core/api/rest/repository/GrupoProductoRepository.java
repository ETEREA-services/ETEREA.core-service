/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.api.rest.kotlin.model.GrupoProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface GrupoProductoRepository extends JpaRepository<GrupoProducto, Long> {
	@Query(value = "SELECT g FROM GrupoProducto g WHERE g.grupoId = :grupoId AND g.productoId = :productoId")
	Optional<GrupoProducto> findByUnique(@Param("grupoId") Integer grupoId, @Param("productoId") Integer productoId);

	List<GrupoProducto> findByProductoId(Integer productoId);
}
