package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ValorException;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.kotlin.repository.ValorRepository;
import org.springframework.stereotype.Service;

@Service
public class ValorService {

    private final ValorRepository repository;

    public ValorService(ValorRepository repository) {
        this.repository = repository;
    }

    public Valor findByValorId(Integer valorId) {
        return repository.findByValorId(valorId).orElseThrow(() -> new ValorException(valorId));
    }

}
