/**
 * 
 */
package eterea.core.service.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	Optional<Cliente> findByNumeroDocumentoAndDocumentoId(String numeroDocumento, Integer documentoId);

	@Query("""
		SELECT
        c
   	FROM
        Cliente c
   	WHERE
        (LOWER(c.razonSocial) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
		  	OR
		  	LOWER(c.numeroDocumento) LIKE LOWER(CONCAT('%', :searchTerm, '%')))
		  AND
		  (CASE WHEN :incluyeBloqueados = false THEN c.bloqueado = 0 ELSE TRUE END)
		  AND
		  c.numeroDocumento IS NOT NULL
		  AND
		  c.numeroDocumento != ''
   	ORDER BY c.razonSocial, c.nombre
		""")
	List<Cliente> findByRazonSocialOrNumeroDocumentoContainingIgnoreCase(@Param("searchTerm") String searchTerm, @Param("incluyeBloqueados") boolean incluyeBloqueados);

	List<Cliente> findAllByClienteIdIn(List<Long> clienteIds);

	Optional<Cliente> findByCuit(String cuit);

   Page<Cliente> findAllBy(Pageable pageable);


}
