/**
 * 
 */
package eterea.core.api.rest.service;

import java.time.OffsetDateTime;

import eterea.core.api.rest.exception.ArticuloFechaException;
import eterea.core.api.rest.repository.IArticuloFechaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ArticuloFecha;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloFechaService {

	@Autowired
	private IArticuloFechaRepository repository;

	public ArticuloFecha findByUnique(String articuloId, OffsetDateTime fecha) {
		return repository.findByArticuloIdAndFecha(articuloId, fecha)
				.orElseThrow(() -> new ArticuloFechaException(articuloId, fecha));
	}

	public ArticuloFecha add(ArticuloFecha articulofecha) {
		repository.save(articulofecha);
		return articulofecha;
	}

	public ArticuloFecha update(ArticuloFecha newarticulofecha, Long articulofechaId) {
		return repository.findByArticulofechaId(articulofechaId).map(articulofecha -> {
			articulofecha = new ArticuloFecha(articulofechaId, newarticulofecha.getArticuloId(),
					newarticulofecha.getFecha(), newarticulofecha.getPrecioUsd(), newarticulofecha.getPrecioArs());
			repository.save(articulofecha);
			return articulofecha;
		}).orElseThrow(() -> new ArticuloFechaException(articulofechaId));
	}
}
