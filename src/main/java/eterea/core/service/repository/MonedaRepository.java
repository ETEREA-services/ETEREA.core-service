/**
 * 
 */
package eterea.core.service.repository;

import java.util.Optional;

import eterea.core.service.kotlin.model.Moneda;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author daniel
 *
 */
public interface MonedaRepository extends JpaRepository<Moneda, Integer> {

	Optional<Moneda> findByMonedaId(Integer monedaId);

}
