/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.PermisoException;
import eterea.core.api.rest.kotlin.model.Permiso;
import eterea.core.api.rest.repository.PermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class PermisoService {

	@Autowired
	private PermisoRepository repository;

	public Permiso findByPermiso(String usuario, String opcion) {
		return repository.findByNombreAndOpcion(usuario, opcion)
				.orElseThrow(() -> new PermisoException(usuario, opcion));
	}

}
