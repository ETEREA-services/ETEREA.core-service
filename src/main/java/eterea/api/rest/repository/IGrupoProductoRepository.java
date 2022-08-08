/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.GrupoProducto;

/**
 * @author daniel
 *
 */
@Repository
public interface IGrupoProductoRepository extends JpaRepository<GrupoProducto, Long> {
	@Query(value = "SELECT g FROM GrupoProducto g WHERE g.grupoId = :grupoId AND g.productoId = :productoId")
	public Optional<GrupoProducto> findByUnique(@Param("grupoId") Integer grupoId, @Param("productoId") Integer productoId);

	public List<GrupoProducto> findByProductoId(Integer productoId);
}
