/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ReservaOrigen;

/**
 * @author daniel
 *
 */
@Repository
public interface IReservaOrigenRepository extends JpaRepository<ReservaOrigen, Integer> {

	public Optional<ReservaOrigen> findByReservaOrigenId(Integer reservaOrigenId);

}
