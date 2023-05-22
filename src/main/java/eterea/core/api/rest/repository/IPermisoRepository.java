/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Permiso;

/**
 * @author daniel
 *
 */
@Repository
public interface IPermisoRepository extends JpaRepository<Permiso, Long> {

	public Optional<Permiso> findByNombreAndOpcion(String usuario, String opcion);

}
