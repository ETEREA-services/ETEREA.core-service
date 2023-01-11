/**
 * 
 */
package eterea.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ArticuloCentro;

/**
 * @author daniel
 *
 */
@Repository
public interface IArticuloCentroRepository extends JpaRepository<ArticuloCentro, Long> {

}
