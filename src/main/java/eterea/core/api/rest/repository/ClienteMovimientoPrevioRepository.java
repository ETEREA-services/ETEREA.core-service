/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.ClienteMovimientoPrevio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteMovimientoPrevioRepository extends JpaRepository<ClienteMovimientoPrevio, Long> {

	public Optional<ClienteMovimientoPrevio> findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId);

}
