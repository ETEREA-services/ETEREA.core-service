/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.exception.ReservaOrigenException;
import eterea.core.service.kotlin.model.ReservaOrigen;
import eterea.core.service.repository.ReservaOrigenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ReservaOrigenService {

	private final ReservaOrigenRepository repository;

	public List<ReservaOrigen> findAll() {
		return repository.findAll();
	}

	public ReservaOrigen findByReservaOrigenId(Integer reservaOrigenId) {
		return repository.findByReservaOrigenId(reservaOrigenId)
				.orElseThrow(() -> new ReservaOrigenException(reservaOrigenId));
	}

}
