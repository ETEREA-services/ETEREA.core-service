/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.List;

import eterea.core.api.rest.kotlin.model.ArticuloMovimientoTemporal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IArticuloMovimientoTemporalRepository extends JpaRepository<ArticuloMovimientoTemporal, Long> {

	public List<ArticuloMovimientoTemporal> findAllByIpAddressAndHwnd(String ipAddress, Long hWnd, Sort sort);

	public List<ArticuloMovimientoTemporal> findAllByIpAddressAndHwndAndCentroId(String ipAddress, Long hWnd,
			Integer centroId, Sort sort);

}
