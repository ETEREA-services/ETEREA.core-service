/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ArticuloMovimiento;
import eterea.api.rest.repository.IArticuloMovimientoRepository;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloMovimientoService {

	@Autowired
	private IArticuloMovimientoRepository repository;

	public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findAllByClienteMovimientoId(clienteMovimientoId);
	}

	public ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento) {
		articuloMovimiento = repository.save(articuloMovimiento);
		return articuloMovimiento;
	}

}
