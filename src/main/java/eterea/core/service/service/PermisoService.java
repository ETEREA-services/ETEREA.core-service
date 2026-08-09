/**
 * 
 */
package eterea.core.service.service;

import eterea.core.service.exception.PermisoException;
import eterea.core.service.kotlin.model.Permiso;
import eterea.core.service.repository.PermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class PermisoService {

	private final PermisoRepository repository;

	public Permiso findByPermiso(String usuario, String opcion) {
		return repository.findByNombreAndOpcion(usuario, opcion)
				.orElseThrow(() -> new PermisoException(usuario, opcion));
	}

}
