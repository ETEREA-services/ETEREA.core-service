/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.ComprobanteAfip;

/**
 * @author daniel
 *
 */
@Repository
public interface IComprobanteAfipRepository extends JpaRepository<ComprobanteAfip, Integer> {

	public Optional<ComprobanteAfip> findByComprobanteAfipId(Integer comprobanteAfipId);

}
