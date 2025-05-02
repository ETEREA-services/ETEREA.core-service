package eterea.core.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import eterea.core.service.kotlin.exception.ArticuloListaPrecioException;
import eterea.core.service.kotlin.model.ArticuloListaPrecio;
import eterea.core.service.kotlin.repository.ArticuloListaPrecioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ArticuloListaPrecioService {

    private final ArticuloListaPrecioRepository repository;

    public ArticuloListaPrecioService(ArticuloListaPrecioRepository repository) {
        this.repository = repository;
    }

    public Page<ArticuloListaPrecio> findAllPublicadosPaginated(Pageable pageable) {
        Sort sort = Sort.by("articulo.rubroId").ascending()
                       .and(Sort.by("articulo.descripcion").ascending());
        
        Pageable pageableWithSort = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            sort
        );
        
        return repository.findAllByPublicarAndArticuloPrecioVentaConIvaGreaterThan(
            (byte) 1, 
            BigDecimal.ZERO, 
            pageableWithSort
        );
    }

    public ArticuloListaPrecio findByArticuloId(String articuloId) {
        return Objects.requireNonNull(repository.findByArticuloId(articuloId)).orElseThrow(() -> new ArticuloListaPrecioException(articuloId));
    }

    public ArticuloListaPrecio publicar(String articuloId, Byte publicar) {
        return repository.findByArticuloId(articuloId).map(articuloListaPrecio -> {
            articuloListaPrecio.setPublicar(publicar);
            articuloListaPrecio = repository.save(articuloListaPrecio);
            logArticuloListaPrecio(articuloListaPrecio);
            return articuloListaPrecio;
        }).orElseGet(() -> {
            var articuloListaPrecio = new ArticuloListaPrecio.Builder()
                    .articuloId(articuloId)
                    .publicar(publicar)
                    .build();
            articuloListaPrecio = repository.save(articuloListaPrecio);
            logArticuloListaPrecio(articuloListaPrecio);
            return articuloListaPrecio;
        });
    }

    public Page<ArticuloListaPrecio> findAllByRubroIdPaginated(Integer rubroId, Pageable pageable) {
        Sort sort = Sort.by("articulo.descripcion").ascending();
        
        Pageable pageableWithSort = PageRequest.of(
            pageable.getPageNumber(),
            pageable.getPageSize(),
            sort
        );
        
        return repository.findAllByPublicarAndArticuloPrecioVentaConIvaGreaterThanAndArticuloRubroId(
            (byte) 1, 
            BigDecimal.ZERO,
            rubroId,
            pageableWithSort
        );
    }

    public ArticuloListaPrecio addOrUpdate(ArticuloListaPrecio articuloListaPrecio) {
        // Buscar si existe un registro con el mismo articuloId
        Optional<ArticuloListaPrecio> existingArticulo = repository.findByArticuloId(Objects.requireNonNull(articuloListaPrecio.getArticuloId()));

        if (existingArticulo.isPresent()) {
            // Actualizar el registro existente
            ArticuloListaPrecio existing = existingArticulo.get();
            existing.setPublicar(articuloListaPrecio.getPublicar());
            return repository.save(existing);
        } else {
            // Crear nuevo registro
            return repository.save(articuloListaPrecio);
        }
    }

    private void logArticuloListaPrecio(ArticuloListaPrecio articuloListaPrecio) {
        try {
            log.debug("ArticuloListaPrecio -> {}", JsonMapper.builder().findAndAddModules().build().writerWithDefaultPrettyPrinter().writeValueAsString(articuloListaPrecio));
        } catch (JsonProcessingException e) {
            log.debug("ArticuloListaPrecio jsonify error -> {}", e.getMessage());
        }
    }

}
