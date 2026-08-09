package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ParametroException;
import eterea.core.service.kotlin.model.Parametro;
import eterea.core.service.kotlin.repository.ParametroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ParametroService {

    private final ParametroRepository repository;

    public Parametro findTop() {
        return Objects.requireNonNull(repository.findTopByOrderByParametroIdDesc()).orElseThrow(ParametroException::new);
    }

}
