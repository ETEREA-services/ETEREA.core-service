/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

	Optional<Habitacion> findByNumero(Integer numero);

}
