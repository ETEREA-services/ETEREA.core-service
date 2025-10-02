/**
 *
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.model.ArticuloFecha;
import eterea.core.service.kotlin.repository.ArticuloFechaRepository;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloFechaService {

    private final ArticuloFechaRepository repository;

    public ArticuloFechaService(ArticuloFechaRepository repository) {
        this.repository = repository;
    }

    public ArticuloFecha findByUnique(String articuloId, OffsetDateTime fecha) {
        return repository.findByArticuloIdAndFecha(articuloId, fecha)
                .orElseThrow(() -> new ArticuloFechaException(articuloId, fecha));
    }

    public ArticuloFecha add(ArticuloFecha articulofecha) {
        repository.save(articulofecha);
        return articulofecha;
    }

    public ArticuloFecha update(ArticuloFecha newarticulofecha, Long articuloFechaId) {
        return repository.findByArticuloFechaId(articuloFechaId).map(articulofecha -> {
            articulofecha = new ArticuloFecha(articuloFechaId, newarticulofecha.getArticuloId(),
                    newarticulofecha.getFecha(), newarticulofecha.getPrecioUsd(), newarticulofecha.getPrecioArs());
            repository.save(articulofecha);
            return articulofecha;
        }).orElseThrow(() -> new ArticuloFechaException(articuloFechaId));
    }

    public List<ArticuloFecha> findAllByArticuloIdAndPeriodo(String articuloId, OffsetDateTime fechaInicio, OffsetDateTime fechaFin) {
        return repository.findAllByArticuloIdAndFechaBetween(articuloId, fechaInicio, fechaFin);
    }

    public List<ArticuloFecha> saveAll(List<ArticuloFecha> toSave) {
        return repository.saveAll(toSave);
    }

    public List<ArticuloFecha> saveOrUpdateAll(List<ArticuloFecha> toSave) {
        if (toSave.isEmpty()) {
            return toSave;
        }
        
        // Step 1: Find existing records (1 query)
        List<String> articuloIds = toSave.stream()
            .map(ArticuloFecha::getArticuloId)
            .distinct()
            .toList();
        List<OffsetDateTime> fechas = toSave.stream()
            .map(ArticuloFecha::getFecha)
            .toList();
        
        List<ArticuloFecha> existing = repository.findAllByArticuloIdInAndFechaIn(articuloIds, fechas);
        
        // Step 2: Create lookup map for existing IDs
        java.util.Map<String, Long> existingIds = existing.stream()
            .collect(java.util.stream.Collectors.toMap(
                e -> e.getArticuloId() + ":" + e.getFecha(),
                ArticuloFecha::getArticuloFechaId
            ));
        
        // Step 3: Set IDs on incoming entities that already exist
        toSave.forEach(entity -> {
            String key = entity.getArticuloId() + ":" + entity.getFecha();
            if (existingIds.containsKey(key)) {
                entity.setArticuloFechaId(existingIds.get(key));
            }
        });
        
        // Step 4: Save all - JPA will INSERT new ones, UPDATE existing ones
        return repository.saveAll(toSave);
    }

}
