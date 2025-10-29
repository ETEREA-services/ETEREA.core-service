/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.LegajoException;
import eterea.core.service.model.Legajo;
import eterea.core.service.model.view.LegajoSearch;
import eterea.core.service.repository.LegajoRepository;
import eterea.core.service.service.view.LegajoSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class LegajoService {

	private final LegajoRepository repository;
	private final LegajoSearchService legajoSearchService;

	public List<Legajo> findAll() {
		return repository.findAll();
	}

	public List<LegajoSearch> findAllBySearch(String search) {
		return legajoSearchService.findAllBySearch(search);
	}

    public Legajo findByLegajoId(Integer legajoId) {
        return repository.findByLegajoId(legajoId).orElseThrow(() -> new LegajoException(legajoId));
    }
    
}
