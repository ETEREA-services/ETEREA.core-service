/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ComprobanteAfipException;
import eterea.core.api.rest.kotlin.model.ComprobanteAfip;
import eterea.core.api.rest.repository.IComprobanteAfipRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ComprobanteAfipService {

	private final IComprobanteAfipRepository repository;

	public ComprobanteAfipService(IComprobanteAfipRepository repository) {
		this.repository = repository;
	}

	public ComprobanteAfip findByComprobanteAfipId(Integer comprobanteAfipId) {
		return repository.findByComprobanteAfipId(comprobanteAfipId)
				.orElseThrow(() -> new ComprobanteAfipException(comprobanteAfipId));
	}

}
