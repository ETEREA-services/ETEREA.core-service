/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.PuntoVenta;

/**
 * @author daniel
 *
 */
@Repository
public interface IPuntoVentaRepository extends JpaRepository<PuntoVenta, Integer> {

	public Optional<PuntoVenta> findByNumero(Integer numero);

}
