/**
 * 
 */
package eterea.api.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.exception.PermisoException;
import eterea.api.rest.model.Permiso;
import eterea.api.rest.repository.IPermisoRepository;

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
