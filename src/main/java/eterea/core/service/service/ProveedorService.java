/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.ProveedorException;
import eterea.core.service.kotlin.model.Proveedor;
import eterea.core.service.repository.ProveedorRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ProveedorService {

	private final ProveedorRepository repository;

	public ProveedorService(ProveedorRepository repository) {
		this.repository = repository;
	}

	public Proveedor findByProveedorId(Integer proveedorId) {
		return repository.findByProveedorId(proveedorId).orElseThrow(() -> new ProveedorException(proveedorId));
	}

}
