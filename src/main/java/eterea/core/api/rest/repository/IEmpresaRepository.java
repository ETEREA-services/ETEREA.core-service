/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IEmpresaRepository extends JpaRepository<Empresa, Integer> {

	public Optional<Empresa> findTopByOrderByEmpresaIdDesc();

}
