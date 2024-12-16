package eterea.core.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.exception.ArticuloBarraException;
import eterea.core.service.kotlin.model.ArticuloBarra;
import eterea.core.service.kotlin.repository.ArticuloBarraRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ArticuloBarraService {

    private final ArticuloBarraRepository repository;

    public ArticuloBarraService(ArticuloBarraRepository repository) {
        this.repository = repository;
    }

    public List<ArticuloBarra> findAllByArticuloId(String articuloId) {
        return repository.findAllByArticuloId(articuloId);
    }

    public ArticuloBarra findByCodigoBarras(String codigoBarras) {
        return Objects.requireNonNull(repository.findByCodigoBarras(codigoBarras)).orElseThrow(() -> new ArticuloBarraException(codigoBarras));
    }

    @Transactional
    public void delete(String codigoBarras) {
        repository.deleteByCodigoBarras(codigoBarras);
    }

    public ArticuloBarra add(ArticuloBarra articuloBarra) {
        logArticuloBarra(articuloBarra);
        articuloBarra = repository.save(articuloBarra);
        logArticuloBarra(articuloBarra);
        return articuloBarra;
    }

    private void logArticuloBarra(ArticuloBarra articuloBarra) {
        try {
            log.debug("ArticuloBarra -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(articuloBarra));
        } catch (JsonProcessingException e) {
            log.debug("ArticuloBarra jsonify error -> {}", e.getMessage());
        }
    }

}
