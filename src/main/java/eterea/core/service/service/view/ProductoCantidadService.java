/**
 * 
 */
package eterea.core.service.service.view;

import java.time.OffsetDateTime;
import java.util.List;

import eterea.core.service.repository.view.IProductoCantidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.core.service.model.view.ProductoCantidad;

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
