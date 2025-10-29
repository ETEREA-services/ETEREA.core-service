package eterea.core.service.service.view;

import eterea.core.service.model.view.LegajoSearch;
import eterea.core.service.repository.view.LegajoSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LegajoSearchService {

    private final LegajoSearchRepository repository;

    public List<LegajoSearch> findAllBySearch(String search) {
        return repository.findTop50BySearchLikeOrderBySearchAsc("%" + search + "%");
    }

}