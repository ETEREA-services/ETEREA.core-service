/**
 * 
 */
package eterea.core.service.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import eterea.core.service.kotlin.exception.ReservaArticuloException;
import eterea.core.service.kotlin.model.ReservaArticulo;
import eterea.core.service.kotlin.repository.ReservaArticuloRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ReservaArticuloService {

	private final ReservaArticuloRepository repository;

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
			return repository.save(reservaArticulo);
		}).orElseThrow(() -> new ReservaArticuloException(reservaArticuloId));
	}

	public boolean equals(List<ReservaArticulo> reservaArticulos1, List<ReservaArticulo> reservaArticulos2) {
		if (reservaArticulos1.size() != reservaArticulos2.size()) {
			return false;
		}

		List<ReservaArticulo> sorted1 = reservaArticulos1.stream()
				.sorted(Comparator.comparing(ReservaArticulo::getArticuloId))
				.toList();

		List<ReservaArticulo> sorted2 = reservaArticulos2.stream()
				.sorted(Comparator.comparing(ReservaArticulo::getArticuloId))
				.toList();

		for (int i = 0; i < sorted1.size(); i++) {
			ReservaArticulo ra1 = sorted1.get(i);
			ReservaArticulo ra2 = sorted2.get(i);

			if (!ra1.getArticuloId().equals(ra2.getArticuloId()) ||
					ra1.getCantidad() != ra2.getCantidad() ||
					ra1.getComision().compareTo(ra2.getComision()) != 0 ||
					ra1.getPrecioUnitarioSinComision().compareTo(ra2.getPrecioUnitarioSinComision()) != 0 ||
					ra1.getPrecioUnitario().compareTo(ra2.getPrecioUnitario()) != 0) {
				return false;
			}
		}
		return true;
	}

}
