/**
 * 
 */
package eterea.api.rest.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.view.ArticuloSearch;
import eterea.api.rest.repository.view.IArticuloSearchRepository;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloSearchService {
	
	@Autowired
	private IArticuloSearchRepository repository;

	public List<ArticuloSearch> findAllByStrings(List<String> conditions) {
		return repository.findAllByStrings(conditions);
	}

}
