/**
 * 
 */
package eterea.core.api.rest.service;

import eterea.core.api.rest.exception.PermisoException;
import eterea.core.api.rest.repository.IPermisoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.Permiso;

/**
 * @author daniel
 *
 */
@Service
public class PermisoService {

	@Autowired
	private IPermisoRepository repository;

	public Permiso findByPermiso(String usuario, String opcion) {
		return repository.findByNombreAndOpcion(usuario, opcion)
				.orElseThrow(() -> new PermisoException(usuario, opcion));
	}

}
