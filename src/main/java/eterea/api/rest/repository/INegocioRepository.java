/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Negocio;

/**
 * @author daniel
 *
 */
@Repository
public interface INegocioRepository extends JpaRepository<Negocio, Integer> {

	public Optional<Negocio> findByNegocioId(Integer negocioId);

}
