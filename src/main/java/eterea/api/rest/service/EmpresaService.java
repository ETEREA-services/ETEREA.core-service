/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.EmpresaException;
import eterea.api.rest.model.Empresa;
import eterea.api.rest.repository.IEmpresaRepository;

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
