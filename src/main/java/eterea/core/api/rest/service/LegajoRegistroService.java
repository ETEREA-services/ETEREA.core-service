/**
 * 
 */
package eterea.core.api.rest.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import eterea.core.api.rest.exception.LegajoRegistroException;
import eterea.core.api.rest.kotlin.model.LegajoRegistro;
import eterea.core.api.rest.repository.LegajoRegistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class LegajoRegistroService {
	@Autowired
	private LegajoRegistroRepository repository;

	public List<LegajoRegistro> findAllByLegajoId(Integer legajoId) {
		return repository.findAllByLegajoId(legajoId);
	}

	public LegajoRegistro findLastByLegajoId(Integer legajoId) {
		return repository.findLastByLegajoId(legajoId).orElseThrow(() -> new LegajoRegistroException(legajoId));
	}

	public LegajoRegistro findLastByLegajoIdAndFecha(Integer legajoId, Date fecha, Time hora) {
		return repository.findLastByLegajoIdAndFecha(legajoId, fecha, hora).orElseThrow(() -> new LegajoRegistroException(legajoId));
	}
}
