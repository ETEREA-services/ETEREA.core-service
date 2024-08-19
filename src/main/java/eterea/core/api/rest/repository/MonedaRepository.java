/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author daniel
 *
 */
public interface MonedaRepository extends JpaRepository<Moneda, Integer> {

	public Optional<Moneda> findByMonedaId(Integer monedaId);

}
