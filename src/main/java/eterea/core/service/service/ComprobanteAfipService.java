/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.ComprobanteAfipException;
import eterea.core.service.kotlin.model.ComprobanteAfip;
import eterea.core.service.repository.ComprobanteAfipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ComprobanteAfipService {

	private final ComprobanteAfipRepository repository;

	public ComprobanteAfip findByComprobanteAfipId(Integer comprobanteAfipId) {
		return repository.findByComprobanteAfipId(comprobanteAfipId)
				.orElseThrow(() -> new ComprobanteAfipException(comprobanteAfipId));
	}

}
