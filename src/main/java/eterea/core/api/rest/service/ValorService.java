package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.ValorException;
import eterea.core.api.rest.kotlin.model.Valor;
import eterea.core.api.rest.kotlin.repository.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValorService {

    private final ValorRepository repository;

    @Autowired
    public ValorService(ValorRepository repository) {
        this.repository = repository;
    }

    public Valor findByValorId(Integer valorId) {
        return repository.findByValorId(valorId).orElseThrow(() -> new ValorException(valorId));
    }

}
