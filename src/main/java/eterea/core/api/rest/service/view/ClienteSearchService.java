/**
 * 
 */
package eterea.core.api.rest.service.view;

import java.util.List;

import eterea.core.api.rest.kotlin.model.view.ClienteSearch;
import eterea.core.api.rest.repository.view.IClienteSearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteSearchService {

	@Autowired
	private IClienteSearchRepository repository;

	public List<ClienteSearch> findAllBySearch(String search) {
		return repository.findTop50BySearchLikeOrderBySearchAsc("%" + search + "%");
	}

}
