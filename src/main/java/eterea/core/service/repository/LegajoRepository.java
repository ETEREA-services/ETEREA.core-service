/**
 * 
 */
package eterea.core.service.repository;

import eterea.core.service.model.Legajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author daniel
 *
 */
@Repository
public interface LegajoRepository extends JpaRepository<Legajo, Integer> {

    Optional<Legajo> findByLegajoId(Integer legajoId);

}
