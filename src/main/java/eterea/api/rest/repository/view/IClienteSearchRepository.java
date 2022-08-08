/**
 * 
 */
package eterea.api.rest.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.view.ClienteSearch;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteSearchRepository extends JpaRepository<ClienteSearch, Long> {

	public List<ClienteSearch> findTop50BySearchLikeOrderBySearchAsc(String search);

}
