/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Electronico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IElectronicoRepository extends JpaRepository<Electronico, Long> {

	public Optional<Electronico> findByComprobanteIdAndPuntoVentaAndNumeroComprobante(Integer comprobanteId,
																					  Integer puntoVenta, Long numeroComprobante);

}
