/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Authorities;

/**
 * @author daniel
 *
 */
@Repository
public interface IAuthoritiesRepository extends JpaRepository<Authorities, Long> {

	public List<Authorities> findAllByClienteId(Long clienteId);

}
