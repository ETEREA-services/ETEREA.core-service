/**
 * 
 */
package eterea.core.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Legajo;

/**
 * @author daniel
 *
 */
@Repository
public interface ILegajoRepository extends JpaRepository<Legajo, Integer> {

}
