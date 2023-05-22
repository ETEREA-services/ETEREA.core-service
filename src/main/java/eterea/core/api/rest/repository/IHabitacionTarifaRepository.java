/**
 * 
 */
package eterea.core.api.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import eterea.core.api.rest.model.HabitacionTarifa;

/**
 * @author daniel
 *
 */
public interface IHabitacionTarifaRepository extends JpaRepository<HabitacionTarifa, Long> {

	public Optional<HabitacionTarifa> findByNumeroAndPaxs(Integer numero, Integer paxs);

	public Optional<HabitacionTarifa> findByHabitacionTarifaId(Long habitacionTarifaId);

}
