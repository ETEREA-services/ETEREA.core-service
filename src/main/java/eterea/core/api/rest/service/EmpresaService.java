/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.EmpresaException;
import eterea.core.api.rest.kotlin.model.Empresa;
import eterea.core.api.rest.repository.IEmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class EmpresaService {

	@Autowired
	private IEmpresaRepository repository;

	public Empresa findTop() {
		return repository.findTopByOrderByEmpresaIdDesc()
				.orElseThrow(() -> new EmpresaException());
	}

}
