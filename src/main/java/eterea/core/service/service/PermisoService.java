/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.PermisoException;
import eterea.core.service.kotlin.model.Permiso;
import eterea.core.service.repository.PermisoRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class PermisoService {

	private final PermisoRepository repository;

	public PermisoService(PermisoRepository repository) {
		this.repository = repository;
	}

	public Permiso findByPermiso(String usuario, String opcion) {
		return repository.findByNombreAndOpcion(usuario, opcion)
				.orElseThrow(() -> new PermisoException(usuario, opcion));
	}

}
