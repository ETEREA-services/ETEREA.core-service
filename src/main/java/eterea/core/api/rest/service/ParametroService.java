package eterea.core.api.rest.service;

import eterea.core.api.rest.kotlin.exception.ParametroException;
import eterea.core.api.rest.kotlin.model.Parametro;
import eterea.core.api.rest.kotlin.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParametroService {

    private final ParametroRepository repository;

    @Autowired
    public ParametroService(ParametroRepository repository) {
        this.repository = repository;
    }

    public Parametro findTop() {
        return repository.findTopByOrderByParametroIdDesc().orElseThrow(ParametroException::new);
    }
}
