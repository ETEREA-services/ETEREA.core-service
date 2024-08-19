/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.ReservaOrigen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ReservaOrigenRepository extends JpaRepository<ReservaOrigen, Integer> {

	public Optional<ReservaOrigen> findByReservaOrigenId(Integer reservaOrigenId);

}
