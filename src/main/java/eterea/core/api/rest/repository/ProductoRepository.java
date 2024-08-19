/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

	List<Producto> findAllByVentaInternet(Byte habilitado, Sort sort);

	Optional<Producto> findByProductoId(Integer productoId);

}
