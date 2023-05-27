/**
 * 
 */
package eterea.core.api.rest.service.view;

import java.util.List;

import eterea.core.api.rest.kotlin.model.view.ClienteActivado;
import eterea.core.api.rest.repository.view.IClienteActivadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ClienteActivadoService {
	@Autowired
	private IClienteActivadoRepository repository;

	public List<ClienteActivado> findAll() {
		return repository.findAll(Sort.by(Sort.Order.asc("nombrefantasia"), Sort.Order.asc("razonsocial")));
	}

}
