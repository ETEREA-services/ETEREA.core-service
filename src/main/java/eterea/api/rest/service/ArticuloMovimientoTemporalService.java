/**
 * 
 */
package eterea.api.rest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import eterea.api.rest.model.ArticuloMovimientoTemporal;
import eterea.api.rest.repository.IArticuloMovimientoTemporalRepository;

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
