package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ValorException;
import eterea.core.service.kotlin.model.Valor;
import eterea.core.service.kotlin.repository.ValorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ValorService {

    private final ValorRepository repository;

    public List<Valor> findAll() {
        return repository.findAll();
    }

    public Valor findByValorId(Integer valorId) {
        return Objects.requireNonNull(repository.findByValorId(valorId)).orElseThrow(() -> new ValorException(valorId));
    }

}
