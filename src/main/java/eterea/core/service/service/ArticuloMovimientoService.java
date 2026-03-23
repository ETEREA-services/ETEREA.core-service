/**
 * 
 */
package eterea.core.service.service;

import java.util.List;
import java.util.Objects;

import eterea.core.service.kotlin.exception.ArticuloMovimientoException;
import eterea.core.service.model.ArticuloMovimiento;
import eterea.core.service.kotlin.repository.ArticuloMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ArticuloMovimientoService {

	private final ArticuloMovimientoRepository repository;

	public List<ArticuloMovimiento> findAllByClienteMovimientoId(Long clienteMovimientoId) {
		return repository.findAllByClienteMovimientoId(clienteMovimientoId);
	}

	public List<ArticuloMovimiento> findAllByStockMovimientoId(Long stockMovimientoId) {
		return repository.findAllByStockMovimientoId(stockMovimientoId);
	}

	public ArticuloMovimiento findByArticuloMovimientoId(Long articuloMovimientoId) {
		return Objects.requireNonNull(repository.findByArticuloMovimientoId(articuloMovimientoId)).orElseThrow(() -> new ArticuloMovimientoException(articuloMovimientoId));
	}

	public ArticuloMovimiento add(ArticuloMovimiento articuloMovimiento) {
		return repository.save(articuloMovimiento);
	}

    public List<ArticuloMovimiento> saveAll(List<ArticuloMovimiento> articuloMovimientos) {
		return repository.saveAll(articuloMovimientos);
    }

}
