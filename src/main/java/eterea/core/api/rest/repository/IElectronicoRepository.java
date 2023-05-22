/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Electronico;

/**
 * @author daniel
 *
 */
@Repository
public interface IElectronicoRepository extends JpaRepository<Electronico, Long> {

	public Optional<Electronico> findByComprobanteIdAndPuntoVentaAndNumeroComprobante(Integer comprobanteId,
			Integer puntoVenta, Long numeroComprobante);

}
