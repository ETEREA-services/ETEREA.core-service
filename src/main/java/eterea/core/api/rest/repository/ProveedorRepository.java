/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {

	public Optional<Proveedor> findByProveedorId(Integer proveedorId);

}
