/**
 * 
 */
package eterea.core.service.repository;

import java.util.List;

import eterea.core.service.kotlin.model.ArticuloMovimientoTemporal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ArticuloMovimientoTemporalRepository extends JpaRepository<ArticuloMovimientoTemporal, Long> {

	List<ArticuloMovimientoTemporal> findAllByIpAddressAndHwnd(String ipAddress, Long hWnd, Sort sort);

	List<ArticuloMovimientoTemporal> findAllByIpAddressAndHwndAndCentroId(String ipAddress, Long hWnd,
			Integer centroId, Sort sort);

}
