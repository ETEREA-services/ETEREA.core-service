/**
 * 
 */
package eterea.core.service.repository;

import java.util.List;
import java.util.Optional;

import eterea.core.service.kotlin.model.ClienteGrupoCupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface ClienteGrupoCupoRepository extends JpaRepository<ClienteGrupoCupo, Long> {

	List<ClienteGrupoCupo> findByClienteIdAndGrupoId(Long clienteId, Integer grupoId);

	Optional<ClienteGrupoCupo> findByClientegrupocupoId(Long clientegrupocupoId);

	Optional<ClienteGrupoCupo> findByClienteIdAndGrupoIdAndDias(Long clienteId, Integer grupoId, Integer dias);

	@Modifying
	void deleteByClienteIdAndGrupoIdAndDias(Long clienteId, Integer grupoId, Integer dias);
}
