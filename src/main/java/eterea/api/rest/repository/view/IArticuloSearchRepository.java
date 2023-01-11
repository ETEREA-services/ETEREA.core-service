/**
 * 
 */
package eterea.api.rest.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.view.ArticuloSearch;

/**
 * @author daniel
 *
 */
@Repository
public interface IArticuloSearchRepository
		extends JpaRepository<ArticuloSearch, String>, IArticuloSearchRepositoryCustom {

}
