/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ReservaOrigenNotFoundException;
import eterea.api.rest.model.ReservaOrigen;
import eterea.api.rest.repository.IReservaOrigenRepository;

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
				.orElseThrow(() -> new ReservaOrigenNotFoundException(reservaOrigenId));
	}

}
