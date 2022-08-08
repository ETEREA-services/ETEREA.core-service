/**
 * 
 */
package eterea.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.Habitacion;

/**
 * @author daniel
 *
 */
@Repository
public interface IHabitacionRepository extends JpaRepository<Habitacion, Integer> {

	public Optional<Habitacion> findByNumero(Integer numero);

}
