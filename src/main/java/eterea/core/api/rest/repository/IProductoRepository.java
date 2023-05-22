/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Producto;

/**
 * @author daniel
 *
 */
@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {
	@Query("SELECT p FROM Producto p WHERE p.ventainternet = :habilitado")
	public List<Producto> findAllByVentaInternet(@Param("habilitado") Byte habilitado, Sort sort);
}
