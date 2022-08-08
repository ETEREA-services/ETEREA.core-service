/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ReservaArticulo;
import eterea.api.rest.repository.IReservaArticuloRepository;

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
