/**
 * 
 */
package eterea.api.rest.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.view.ClienteSearch;
import eterea.api.rest.repository.view.IClienteSearchRepository;

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
