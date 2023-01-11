/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.Cuenta;
import eterea.api.rest.repository.ICuentaRepository;

/**
 * @author daniel
 *
 */
@Service
public class CuentaService {

	@Autowired
	private ICuentaRepository repository;

	public List<Cuenta> findAll() {
		return repository.findAll();
	}

}
