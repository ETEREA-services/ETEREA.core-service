/**
 * 
 */
package eterea.core.service.repository;

import java.util.Optional;

import eterea.core.service.kotlin.model.HabitacionTarifa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author daniel
 *
 */
public interface HabitacionTarifaRepository extends JpaRepository<HabitacionTarifa, Long> {

	Optional<HabitacionTarifa> findByNumeroAndPaxs(Integer numero, Integer paxs);

	Optional<HabitacionTarifa> findByHabitacionTarifaId(Long habitacionTarifaId);

}
