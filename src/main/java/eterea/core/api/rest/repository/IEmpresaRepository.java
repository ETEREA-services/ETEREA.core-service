/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.Empresa;

/**
 * @author daniel
 *
 */
@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

	public Optional<Empresa> findTopByOrderByEmpresaIdDesc();

}
