/**
 *
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.model.ArticuloFecha;
import eterea.core.service.kotlin.repository.ArticuloFechaRepository;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

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
        ArticuloFecha articulofecha = repository.findByArticuloFechaId(articuloFechaId)
                .orElseThrow(() -> new ArticuloFechaException(articuloFechaId));

        articulofecha.setArticuloId(newarticulofecha.getArticuloId());
        articulofecha.setFecha(newarticulofecha.getFecha());
        articulofecha.setPrecioUsd(newarticulofecha.getPrecioUsd());
        articulofecha.setPrecioArs(newarticulofecha.getPrecioArs());

        return repository.save(articulofecha);
    }

    public List<ArticuloFecha> findAllByArticuloIdAndPeriodo(String articuloId, OffsetDateTime fechaInicio, OffsetDateTime fechaFin) {
        return repository.findAllByArticuloIdAndFechaBetween(articuloId, fechaInicio, fechaFin);
    }

    public List<ArticuloFecha> saveAll(List<ArticuloFecha> toSave) {
        return repository.saveAll(toSave);
    }

}
