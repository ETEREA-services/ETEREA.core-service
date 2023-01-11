/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ArticuloBarra;

/**
 * @author daniel
 *
 */
@Repository
public interface IArticuloBarraRepository extends JpaRepository<ArticuloBarra, String> {

	public Optional<ArticuloBarra> findByCodigoBarras(String codigoBarras);

}
