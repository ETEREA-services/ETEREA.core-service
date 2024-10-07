/**
 * 
 */
package eterea.core.service.repository;

import eterea.core.service.kotlin.model.HabitacionTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface HabitacionTipoRepository extends JpaRepository<HabitacionTipo, Integer> {

}
