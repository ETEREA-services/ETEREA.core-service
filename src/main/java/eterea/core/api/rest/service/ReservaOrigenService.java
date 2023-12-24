/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.ReservaOrigenException;
import eterea.core.api.rest.kotlin.model.ReservaOrigen;
import eterea.core.api.rest.repository.IReservaOrigenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ReservaOrigenService {

	@Autowired
	private IReservaOrigenRepository repository;

	public List<ReservaOrigen> findAll() {
		return repository.findAll();
	}

	public ReservaOrigen findByReservaOrigenId(Integer reservaOrigenId) {
		return repository.findByReservaOrigenId(reservaOrigenId)
				.orElseThrow(() -> new ReservaOrigenException(reservaOrigenId));
	}

}
