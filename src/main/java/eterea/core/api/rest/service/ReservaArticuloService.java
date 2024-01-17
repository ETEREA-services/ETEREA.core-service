/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import eterea.core.api.rest.kotlin.exception.ReservaArticuloException;
import eterea.core.api.rest.kotlin.model.ReservaArticulo;
import eterea.core.api.rest.kotlin.repository.ReservaArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ReservaArticuloService {

	private final ReservaArticuloRepository repository;

	@Autowired
	public ReservaArticuloService(ReservaArticuloRepository repository) {
		this.repository = repository;
	}

	public List<ReservaArticulo> findAllByReservaId(Long reservaId) {
		return repository.findAllByReservaId(reservaId);
	}

    public List<ReservaArticulo> findAllByVoucherId(Long reservaId, Long voucherId) {
		return repository.findAllByReservaIdAndVoucherId(reservaId, voucherId);
    }

	@Transactional
	public void deleteByReservaArticuloId(Long reservaArticuloId) {
		repository.deleteByReservaArticuloId(reservaArticuloId);
	}

	public List<ReservaArticulo> saveAll(List<ReservaArticulo> reservaArticulos) {
		return repository.saveAll(reservaArticulos);
	}

	public ReservaArticulo add(ReservaArticulo reservaArticulo) {
		return repository.save(reservaArticulo);
	}

    public ReservaArticulo update(ReservaArticulo newReservaArticulo, Long reservaArticuloId) {
		return repository.findByReservaArticuloId(reservaArticuloId).map(reservaArticulo -> {
			reservaArticulo = new ReservaArticulo.Builder()
					.reservaArticuloId(reservaArticuloId)
					.negocioId(newReservaArticulo.getNegocioId())
					.reservaId(newReservaArticulo.getReservaId())
					.voucherId(newReservaArticulo.getVoucherId())
					.articuloId(newReservaArticulo.getArticuloId())
					.cantidad(newReservaArticulo.getCantidad())
					.comision(newReservaArticulo.getComision())
					.precioUnitarioSinComision(newReservaArticulo.getPrecioUnitarioSinComision())
					.precioUnitario(newReservaArticulo.getPrecioUnitario())
					.precioCompra(newReservaArticulo.getPrecioCompra())
					.observaciones(newReservaArticulo.getObservaciones())
					.build();
			return reservaArticulo;
		}).orElseThrow(() -> new ReservaArticuloException(reservaArticuloId));
    }

}
