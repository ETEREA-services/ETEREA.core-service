/**
 * 
 */
package eterea.api.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.ClienteGrupoCupo;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteGrupoCupoRepository extends JpaRepository<ClienteGrupoCupo, Long> {

	public List<ClienteGrupoCupo> findByClienteIdAndGrupoId(Long clienteId, Integer grupoId);

	public Optional<ClienteGrupoCupo> findByClientegrupocupoId(Long clientegrupocupoId);

	public Optional<ClienteGrupoCupo> findByClienteIdAndGrupoIdAndDias(Long clienteId, Integer grupoId, Integer dias);

	@Modifying
	public void deleteByClienteIdAndGrupoIdAndDias(Long clienteId, Integer grupoId, Integer dias);
}
