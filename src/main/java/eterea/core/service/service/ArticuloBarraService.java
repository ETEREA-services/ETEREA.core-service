package eterea.core.service.service;

import eterea.core.service.kotlin.exception.ArticuloBarraException;
import eterea.core.service.kotlin.model.ArticuloBarra;
import eterea.core.service.kotlin.repository.ArticuloBarraRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ArticuloBarraService {

    private final ArticuloBarraRepository repository;

    public ArticuloBarraService(ArticuloBarraRepository repository) {
        this.repository = repository;
    }

    public ArticuloBarra findByCodigoBarras(String codigobarras) {
        return Objects.requireNonNull(repository.findByCodigoBarras(codigobarras)).orElseThrow(() -> new ArticuloBarraException(codigobarras));
    }

}
