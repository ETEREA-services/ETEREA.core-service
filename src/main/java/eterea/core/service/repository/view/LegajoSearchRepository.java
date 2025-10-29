package eterea.core.service.repository.view;

import eterea.core.service.model.view.LegajoSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LegajoSearchRepository extends JpaRepository<LegajoSearch, Integer> {

    List<LegajoSearch> findTop50BySearchLikeOrderBySearchAsc(String search);

}