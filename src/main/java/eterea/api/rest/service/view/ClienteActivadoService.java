/**
 * 
 */
package eterea.api.rest.service.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.view.ClienteActivado;
import eterea.api.rest.repository.view.IClienteActivadoRepository;

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
