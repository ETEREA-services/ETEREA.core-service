/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.NegocioException;
import eterea.core.service.kotlin.model.Negocio;
import eterea.core.service.kotlin.repository.NegocioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author daniel
 *
 */
@Service
@Slf4j
public class NegocioService {

	private final NegocioRepository repository;

	public NegocioService(NegocioRepository repository) {
		this.repository = repository;
	}

	public Negocio findByNegocioId(Integer negocioId) {
		var negocio = Objects.requireNonNull(repository.findByNegocioId(negocioId)).orElseThrow(() -> new NegocioException(negocioId));
        assert negocio != null;
        negocio.setIpAddress(negocio.getDatabaseIpVpn());
		negocio.setBackendServer(negocio.getBackendIpVpn());
		return negocio;
	}

    public List<Negocio> findAllByCopyArticulo(Byte copyArticulo) {
		log.debug("Processing findAllByCopyArticulo");
        return repository.findAllByCopyArticulo(copyArticulo);
    }

}
