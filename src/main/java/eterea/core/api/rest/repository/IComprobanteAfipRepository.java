/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.ComprobanteAfip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IComprobanteAfipRepository extends JpaRepository<ComprobanteAfip, Integer> {

	Optional<ComprobanteAfip> findByComprobanteAfipId(Integer comprobanteAfipId);

}
