package eterea.core.service.service;

import eterea.core.service.kotlin.model.CentroStock;
import eterea.core.service.kotlin.repository.CentroStockRepository;
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
