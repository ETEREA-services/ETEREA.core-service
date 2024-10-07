/**
 * 
 */
package eterea.core.service.repository.view;

import java.util.List;

import eterea.core.service.kotlin.model.view.ClienteSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteSearchRepository extends JpaRepository<ClienteSearch, Long> {

	public List<ClienteSearch> findTop50BySearchLikeOrderBySearchAsc(String search);

}
