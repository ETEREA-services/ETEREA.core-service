/**
 * 
 */
package eterea.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ConceptoFacturado;

/**
 * @author daniel
 *
 */
@Repository
public interface IConceptoFacturadoRepository extends JpaRepository<ConceptoFacturado, Long> {

}
