/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ProveedorException;
import eterea.core.api.rest.repository.IProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Proveedor;

/**
 * @author daniel
 *
 */
@Service
public class ProveedorService {

	@Autowired
	private IProveedorRepository repository;

	public Proveedor findByProveedorId(Integer proveedorId) {
		return repository.findByProveedorId(proveedorId).orElseThrow(() -> new ProveedorException(proveedorId));
	}

}
