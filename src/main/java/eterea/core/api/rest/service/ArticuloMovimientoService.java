/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.kotlin.exception.ArticuloMovimientoException;
import eterea.core.api.rest.kotlin.model.ArticuloMovimiento;
import eterea.core.api.rest.kotlin.repository.IArticuloMovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloMovimientoService {

	private final IArticuloMovimientoRepository repository;

	@Autowired
	public ArticuloMovimientoService(IArticuloMovimientoRepository repository) {
		this.repository = repository;
	}

	public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findAllByClienteMovimientoId(clienteMovimientoId);
	}

	public ArticuloMovimiento findByArticuloMovimientoId(Long articuloMovimientoId) {
		return repository.findByArticuloMovimientoId(articuloMovimientoId).orElseThrow(() -> new ArticuloMovimientoException(articuloMovimientoId));
	}

	public ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento) {
		articuloMovimiento = repository.save(articuloMovimiento);
		return articuloMovimiento;
	}

}
