/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.repository.IReservaArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ReservaArticulo;

/**
 * @author daniel
 *
 */
@Service
public class ReservaArticuloService {

	@Autowired
	private IReservaArticuloRepository repository;

	public List<ReservaArticulo> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}
}
