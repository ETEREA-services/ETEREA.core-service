/**
 * 
 */
package eterea.core.service.repository;

import java.util.Optional;

import eterea.core.service.kotlin.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	Optional<Cliente> findByClienteId(Long clienteId);

	Optional<Cliente> findTopByNumeroDocumento(String numeroDocumento);

	Optional<Cliente> findTopByOrderByClienteIdDesc();

}
