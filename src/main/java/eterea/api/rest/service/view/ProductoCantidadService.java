/**
 * 
 */
package eterea.api.rest.service.view;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.view.ProductoCantidad;
import eterea.api.rest.repository.view.IProductoCantidadRepository;

/**
 * @author daniel
 *
 */
@Service
public class ProductoCantidadService {
	
	@Autowired
	private IProductoCantidadRepository repository;

	public List<ProductoCantidad> findAllByFechaServicio(OffsetDateTime fechaServicio) {
		return repository.findAllByFechaServicio(fechaServicio);
	}

}
