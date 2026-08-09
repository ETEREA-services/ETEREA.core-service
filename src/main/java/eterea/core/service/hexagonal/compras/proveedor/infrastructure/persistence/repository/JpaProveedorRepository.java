/**
 * 
 */
package eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.repository;

import java.util.Optional;

import eterea.core.service.hexagonal.compras.proveedor.infrastructure.persistence.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface JpaProveedorRepository extends JpaRepository<ProveedorEntity, Integer> {

	Optional<ProveedorEntity> findByProveedorId(Integer proveedorId);

}
