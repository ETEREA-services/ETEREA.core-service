/**
 * 
 */
package eterea.core.service.service.view;

import java.util.List;

import eterea.core.service.kotlin.model.view.ClienteActivado;
import eterea.core.service.repository.view.IClienteActivadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ClienteActivadoService {

	private final IClienteActivadoRepository repository;

	public List<ClienteActivado> findAll() {
		return repository.findAll(Sort.by(Sort.Order.asc("nombrefantasia"), Sort.Order.asc("razonsocial")));
	}

}
