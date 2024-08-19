/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;

import eterea.core.api.rest.kotlin.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long> {

	public List<Authorities> findAllByClienteId(Long clienteId);

}
