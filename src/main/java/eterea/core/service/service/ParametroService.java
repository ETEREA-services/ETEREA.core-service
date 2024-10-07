package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ParametroException;
import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.kotlin.repository.ParametroRepository;
import org.springframework.stereotype.Service;

@Service
public class ParametroService {

    private final ParametroRepository repository;

    public ParametroService(ParametroRepository repository) {
        this.repository = repository;
    }

    public Parametro findTop() {
        return repository.findTopByOrderByParametroIdDesc().orElseThrow(ParametroException::new);
    }
}
