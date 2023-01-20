/**
 * 
 */
package eterea.api.rest.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ArticuloFechaException;
import eterea.api.rest.model.ArticuloFecha;
import eterea.api.rest.repository.IArticuloFechaRepository;

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
