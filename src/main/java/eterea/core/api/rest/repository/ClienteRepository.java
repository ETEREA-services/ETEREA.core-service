/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByClienteId(Long clienteId);

	public Optional<Cliente> findTopByNumeroDocumento(String numeroDocumento);

	public Optional<Cliente> findTopByOrderByClienteIdDesc();

}
