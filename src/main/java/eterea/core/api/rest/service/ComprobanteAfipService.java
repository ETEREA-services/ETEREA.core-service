/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ComprobanteAfipException;
import eterea.core.api.rest.repository.IComprobanteAfipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ComprobanteAfip;

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
				.orElseThrow(() -> new ComprobanteAfipException(comprobanteAfipId));
	}

}
