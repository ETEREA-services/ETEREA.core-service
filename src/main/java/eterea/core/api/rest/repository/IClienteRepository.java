/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Cliente;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {

	public Optional<Cliente> findByClienteId(Long clienteId);

	public Optional<Cliente> findTopByNumeroDocumento(String numeroDocumento);

	public Optional<Cliente> findTopByOrderByClienteIdDesc();

}
