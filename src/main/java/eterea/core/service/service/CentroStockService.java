package eterea.core.service.service;

import eterea.core.service.kotlin.model.CentroStock;
import eterea.core.service.kotlin.repository.CentroStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CentroStockService {

    private final CentroStockRepository repository;

    public List<CentroStock> findAll() {
        return repository.findAll();
    }

}
