/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.NegocioException;
import eterea.core.service.kotlin.model.Negocio;
import eterea.core.service.kotlin.repository.NegocioRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class NegocioService {

	private final NegocioRepository repository;

	public NegocioService(NegocioRepository repository) {
		this.repository = repository;
	}

	public Negocio findByNegocioId(Integer negocioId) {
		var negocio = repository.findByNegocioId(negocioId).orElseThrow(() -> new NegocioException(negocioId));
		negocio.setIpAddress(negocio.getDatabaseIpVpn());
		negocio.setBackendServer(negocio.getBackendIpVpn());
		return negocio;
	}

}
