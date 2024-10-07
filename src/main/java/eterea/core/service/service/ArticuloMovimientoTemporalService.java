/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.model.ArticuloMovimientoTemporal;
import eterea.core.service.repository.ArticuloMovimientoTemporalRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloMovimientoTemporalService {

	private final ArticuloMovimientoTemporalRepository repository;

	public ArticuloMovimientoTemporalService(ArticuloMovimientoTemporalRepository repository) {
		this.repository = repository;
	}

	public List<ArticuloMovimientoTemporal> findAllByHwnd(String ipAddress, Long hWnd, Integer centroId) {
		Sort sort = Sort.by("item").ascending()
				.and(Sort.by("item2").descending().and(Sort.by("articuloMovimientoTemporalId").ascending()));
		if (centroId == null) {
			return repository.findAllByIpAddressAndHwnd(ipAddress, hWnd, sort);
		}
		return repository.findAllByIpAddressAndHwndAndCentroId(ipAddress, hWnd, centroId, sort);
	}

}
