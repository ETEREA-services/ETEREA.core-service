package eterea.core.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.exception.ArticuloBarraException;
import eterea.core.service.kotlin.model.ArticuloBarra;
import eterea.core.service.kotlin.repository.ArticuloBarraRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class ArticuloBarraService {

    private final ArticuloBarraRepository repository;

    public List<ArticuloBarra> findAllByArticuloId(String articuloId) {
        return repository.findAllByArticuloId(articuloId);
    }

    public ArticuloBarra findByCodigoBarras(String codigoBarras) {
        log.debug("\n\nProcessing ArticuloBarraService.findByCodigoBarras\n\n");
        ArticuloBarra articuloBarra = repository.findByCodigoBarras(codigoBarras).orElseThrow(() -> new ArticuloBarraException(codigoBarras));
        log.debug("\n\nArticuloBarra -> {}\n\n", articuloBarra.jsonify());
        return articuloBarra;
    }

    @Transactional
    public void delete(String codigoBarras) {
        repository.deleteByCodigoBarras(codigoBarras);
    }

    public ArticuloBarra add(ArticuloBarra articuloBarra) {
        log.debug("\n\nProcessing ArticuloBarraService.add\n\n");
        articuloBarra = repository.save(articuloBarra);
        log.debug("\n\nArticuloBarra -> {}\n\n", articuloBarra.jsonify());
        return articuloBarra;
    }

}
