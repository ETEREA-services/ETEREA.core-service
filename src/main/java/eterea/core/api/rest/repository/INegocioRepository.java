/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Negocio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface INegocioRepository extends JpaRepository<Negocio, Integer> {

	public Optional<Negocio> findByNegocioId(Integer negocioId);

}
