/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.EmpresaException;
import eterea.core.service.kotlin.model.Empresa;
import eterea.core.service.kotlin.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class EmpresaService {

	private final EmpresaRepository repository;

	public EmpresaService(EmpresaRepository repository) {
		this.repository = repository;
	}

	public Empresa findTop() {
		return repository.findTopByOrderByEmpresaIdDesc()
				.orElseThrow(EmpresaException::new);
	}

}
