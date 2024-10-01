package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.model.CentroStock;
import eterea.core.api.rest.kotlin.repository.CentroStockRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentroStockService {

    private final CentroStockRepository repository;

    public CentroStockService(CentroStockRepository repository) {
        this.repository = repository;
    }

    public List<CentroStock> findAll() {
        return repository.findAll();
    }

}
