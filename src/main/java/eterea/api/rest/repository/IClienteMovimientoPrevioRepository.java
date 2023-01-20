/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ClienteMovimientoPrevio;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteMovimientoPrevioRepository extends JpaRepository<ClienteMovimientoPrevio, Long> {

	public Optional<ClienteMovimientoPrevio> findByClienteMovimientoPrevioId(Long clienteMovimientoPrevioId);

}
