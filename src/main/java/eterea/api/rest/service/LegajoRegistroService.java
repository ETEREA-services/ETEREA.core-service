/**
 * 
 */
package eterea.api.rest.service;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.LegajoRegistroException;
import eterea.api.rest.model.LegajoRegistro;
import eterea.api.rest.repository.ILegajoRegistroRepository;

/**
 * @author daniel
 *
 */
@Service
public class LegajoRegistroService {
	@Autowired
	private ILegajoRegistroRepository repository;

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
