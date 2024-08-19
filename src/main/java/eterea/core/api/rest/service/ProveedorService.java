/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.ProveedorException;
import eterea.core.api.rest.kotlin.model.Proveedor;
import eterea.core.api.rest.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ProveedorService {

	@Autowired
	private ProveedorRepository repository;

	public Proveedor findByProveedorId(Integer proveedorId) {
		return repository.findByProveedorId(proveedorId).orElseThrow(() -> new ProveedorException(proveedorId));
	}

}
