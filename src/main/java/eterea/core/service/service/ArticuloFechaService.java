/**
 * 
 */
package eterea.core.service.service;

import java.time.OffsetDateTime;

import eterea.core.service.exception.ArticuloFechaException;
import eterea.core.service.kotlin.model.ArticuloFecha;
import eterea.core.service.kotlin.repository.ArticuloFechaRepository;
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
		return repository.findByArticuloFechaId(articuloFechaId).map(articulofecha -> {
			articulofecha = new ArticuloFecha(articuloFechaId, newarticulofecha.getArticuloId(),
					newarticulofecha.getFecha(), newarticulofecha.getPrecioUsd(), newarticulofecha.getPrecioArs());
			repository.save(articulofecha);
			return articulofecha;
		}).orElseThrow(() -> new ArticuloFechaException(articuloFechaId));
	}

}
