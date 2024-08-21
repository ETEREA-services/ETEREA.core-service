/**
 * 
 */
package eterea.core.api.rest.repository;

import eterea.core.api.rest.kotlin.model.Legajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface LegajoRepository extends JpaRepository<Legajo, Integer> {

}