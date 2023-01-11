/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Parametro;

/**
 * @author daniel
 *
 */
@Repository
public interface IParametroRepository extends JpaRepository<Parametro, Long> {

	public Optional<Parametro> findTopByOrderByParametroIdDesc();

}
