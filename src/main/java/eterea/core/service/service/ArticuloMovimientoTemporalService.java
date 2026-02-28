/**
 * 
 */
package eterea.core.service.service;

import java.util.List;

import eterea.core.service.kotlin.model.ArticuloMovimientoTemporal;
import eterea.core.service.repository.ArticuloMovimientoTemporalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author daniel
 *
 */
@Service
@RequiredArgsConstructor
public class ArticuloMovimientoTemporalService {

	private final ArticuloMovimientoTemporalRepository repository;

	public List<ArticuloMovimientoTemporal> findAllByHWnd(String ipAddress, Long hWnd, Integer centroId) {
		Sort sort = Sort.by("item").ascending()
				.and(Sort.by("item2").descending().and(Sort.by("articuloMovimientoTemporalId").ascending()));
		if (centroId == null || centroId == 0) {
			return repository.findAllByIpAddressAndHWnd(ipAddress, hWnd, sort);
		}
		return repository.findAllByIpAddressAndHWndAndCentroId(ipAddress, hWnd, centroId, sort);
	}

}
