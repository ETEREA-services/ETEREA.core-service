/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ComprobanteAfipNotFoundException;
import eterea.api.rest.model.ComprobanteAfip;
import eterea.api.rest.repository.IComprobanteAfipRepository;

/**
 * @author daniel
 *
 */
@Service
public class ComprobanteAfipService {

	@Autowired
	private IComprobanteAfipRepository repository;

	public ComprobanteAfip findByComprobanteAfipId(Integer comprobanteAfipId) {
		return repository.findByComprobanteAfipId(comprobanteAfipId)
				.orElseThrow(() -> new ComprobanteAfipNotFoundException(comprobanteAfipId));
	}

}
