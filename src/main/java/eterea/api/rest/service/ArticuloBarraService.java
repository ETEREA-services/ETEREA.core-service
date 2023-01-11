/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ArticuloBarraNotFoundException;
import eterea.api.rest.model.ArticuloBarra;
import eterea.api.rest.repository.IArticuloBarraRepository;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloBarraService {

	@Autowired
	private IArticuloBarraRepository repository;

	public ArticuloBarra findByCodigoBarras(String codigoBarras) {
		return repository.findByCodigoBarras(codigoBarras)
				.orElseThrow(() -> new ArticuloBarraNotFoundException(codigoBarras));
	}

}
