/**
 * 
 */
package eterea.core.service.repository.view;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.service.model.view.HabitacionMovimientoExtended;

/**
 * @author daniel
 *
 */
@Repository
public interface IHabitacionMovimientoExtendedRepository extends JpaRepository<HabitacionMovimientoExtended, Long> {

	public List<HabitacionMovimientoExtended> findAllByFechaIngresoBetween(OffsetDateTime desde, OffsetDateTime hasta);

	public List<HabitacionMovimientoExtended> findAllByFechaSalidaBetween(OffsetDateTime desde, OffsetDateTime hasta);

	public List<HabitacionMovimientoExtended> findAllByFechaIngresoLessThanEqualAndFechaSalidaGreaterThanEqual(
			OffsetDateTime desde, OffsetDateTime hasta);

	public Optional<HabitacionMovimientoExtended> findByNumeroReserva(Long numeroReserva);

}
