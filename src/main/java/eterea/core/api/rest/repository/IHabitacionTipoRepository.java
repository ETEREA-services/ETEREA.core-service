/**
 * 
 */
package eterea.core.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.HabitacionTipo;

/**
 * @author daniel
 *
 */
@Repository
public interface IHabitacionTipoRepository extends JpaRepository<HabitacionTipo, Integer> {

}
