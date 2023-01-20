/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.ProveedorException;
import eterea.api.rest.model.Proveedor;
import eterea.api.rest.repository.IProveedorRepository;

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
