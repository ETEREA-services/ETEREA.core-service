/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import eterea.core.api.rest.kotlin.model.HabitacionTarifa;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author daniel
 *
 */
public interface HabitacionTarifaRepository extends JpaRepository<HabitacionTarifa, Long> {

	public Optional<HabitacionTarifa> findByNumeroAndPaxs(Integer numero, Integer paxs);

	public Optional<HabitacionTarifa> findByHabitacionTarifaId(Long habitacionTarifaId);

}
