/**
 * 
 */
package eterea.core.api.rest.service;

import java.util.List;

import eterea.core.api.rest.repository.IArticuloMovimientoTemporalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.core.api.rest.model.ArticuloMovimientoTemporal;

/**
 * @author daniel
 *
 */
@Service
public class ArticuloMovimientoTemporalService {

	@Autowired
	private IArticuloMovimientoTemporalRepository repository;

	public List<ArticuloMovimientoTemporal> findAllByHwnd(String ipAddress, Long hWnd, Integer centroId) {
		Sort sort = Sort.by("item").ascending()
				.and(Sort.by("item2").descending().and(Sort.by("articuloMovimientoTemporalId").ascending()));
		if (centroId == null) {
			return repository.findAllByIpAddressAndHwnd(ipAddress, hWnd, sort);
		}
		return repository.findAllByIpAddressAndHwndAndCentroId(ipAddress, hWnd, centroId, sort);
	}

}
