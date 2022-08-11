/**
 *
 */
package eterea.api.rest.repository;

import eterea.api.rest.model.Grupo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author daniel
 */
@Repository
public interface IGrupoRepository extends JpaRepository<Grupo, Integer> {
    public List<Grupo> findAllByVentainternet(Byte habilitado, Sort sort);

}
