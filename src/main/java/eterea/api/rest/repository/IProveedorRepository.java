/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Proveedor;

/**
 * @author daniel
 *
 */
@Repository
public interface IProveedorRepository extends JpaRepository<Proveedor, Integer> {

	public Optional<Proveedor> findByProveedorId(Integer proveedorId);

}
