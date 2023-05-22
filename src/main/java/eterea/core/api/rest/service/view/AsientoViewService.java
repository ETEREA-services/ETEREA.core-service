package eterea.core.api.rest.service.view;

import eterea.core.api.rest.model.view.AsientoView;
import eterea.core.api.rest.repository.view.AsientoViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AsientoViewService {

    @Autowired
    private AsientoViewRepository repository;

    public List<AsientoView> findAllDifferences(OffsetDateTime desde, OffsetDateTime hasta) {
        return repository.findAllByFechaBetween(desde, hasta).stream().filter(asiento -> asiento.getDebe().compareTo(asiento.getHaber()) != 0).collect(Collectors.toList());
    }
}
