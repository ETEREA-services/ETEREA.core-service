/**
 * 
 */
package eterea.core.service.repository;

import java.util.Optional;

import eterea.core.service.kotlin.model.ClienteMovimientoPrevio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteMovimientoPrevioRepository extends JpaRepository<ClienteMovimientoPrevio, Long> {

	Optional<ClienteMovimientoPrevio> findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId);

}
