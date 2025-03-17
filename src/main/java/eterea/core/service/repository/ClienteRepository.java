/**
 * 
 */
package eterea.core.service.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	@Query("""
		SELECT
			c
		FROM
			Clientes c
		WHERE
			c.razon LIKE ':searchTerm%'
			AND
			c.bloqueado = 0
		ORDER BY c.razon, c.nombre
		""")
	List<Cliente> findByNombreContainingIgnoreCase(@Param("searchTerm") String searchTerm);

}
