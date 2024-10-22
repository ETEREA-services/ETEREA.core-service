/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.exception.ArticuloMovimientoException;
import eterea.core.service.kotlin.model.ArticuloMovimiento;
import eterea.core.service.kotlin.repository.ArticuloMovimientoRepository;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloMovimientoService {

	private final ArticuloMovimientoRepository repository;

	public ArticuloMovimientoService(ArticuloMovimientoRepository repository) {
		this.repository = repository;
	}

	public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findAllByClienteMovimientoId(clienteMovimientoId);
	}

	public ArticuloMovimiento findByArticuloMovimientoId(Long articuloMovimientoId) {
		return repository.findByArticuloMovimientoId(articuloMovimientoId).orElseThrow(() -> new ArticuloMovimientoException(articuloMovimientoId));
	}

	public ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento) {
		return repository.save(articuloMovimiento);
	}

    public List<ArticuloMovimiento> saveAll(List<ArticuloMovimiento> articuloMovimientos) {
		return repository.saveAll(articuloMovimientos);
    }

}
