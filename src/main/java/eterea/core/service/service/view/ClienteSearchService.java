/**
 * 
 */
package eterea.core.service.service.view;

import java.util.List;

import eterea.core.service.kotlin.model.view.ClienteSearch;
import eterea.core.service.repository.view.IClienteSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ClienteSearchService {

	private final IClienteSearchRepository repository;

	public List<ClienteSearch> findAllBySearch(String search) {
		return repository.findTop50BySearchLikeOrderBySearchAsc("%" + search + "%");
	}

}
