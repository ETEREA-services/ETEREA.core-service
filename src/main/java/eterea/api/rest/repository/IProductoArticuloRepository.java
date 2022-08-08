/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ProductoArticulo;

/**
 * @author daniel
 *
 */
@Repository
public interface IProductoArticuloRepository extends JpaRepository<ProductoArticulo, Long> {

	public List<ProductoArticulo> findAllByProductoId(Integer productoId);
}
