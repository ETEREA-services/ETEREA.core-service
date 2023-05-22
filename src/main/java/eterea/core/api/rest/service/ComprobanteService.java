/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.exception.ComprobanteException;
import eterea.core.api.rest.repository.IComprobanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Comprobante;

/**
 * @author daniel
 *
 */
@Service
public class ComprobanteService {

	@Autowired
	private IComprobanteRepository repository;

	public List<Comprobante> findAllAsociables() {
		return repository.findAllByModuloAndDebitaAndAsociado(3, (byte) 1, (byte) 0);
	}

	public List<Comprobante> findAllByModulo(Integer modulo, Byte debita, Integer comprobanteId) {
//		debita < 2 && comprobanteId > 0
		if (debita < 2 && comprobanteId > 0) {
			return repository.findAllByModuloAndTransferirAndInvisibleAndDebitaAndComprobanteId(modulo, (byte) 0,
					(byte) 0, debita, comprobanteId);
		}
//		debita < 2 && comprobanteId = 0
		if (debita < 2) {
			return repository.findAllByModuloAndTransferirAndInvisibleAndDebita(modulo, (byte) 0, (byte) 0, (byte) 0);
		}
//		debita = 2 && comprobanteId > 0
		if (comprobanteId > 0) {
			return repository.findAllByModuloAndTransferirAndInvisibleAndComprobanteId(modulo, (byte) 0, (byte) 0,
					comprobanteId);
		}
//		debita = 2 && comprobanteId = 0
		return repository.findAllByModuloAndTransferirAndInvisible(modulo, (byte) 0, (byte) 0);
	}

	public Comprobante findByComprobanteId(Integer comprobanteId) {
		return repository.findByComprobanteId(comprobanteId)
				.orElseThrow(() -> new ComprobanteException(comprobanteId));
	}

}
