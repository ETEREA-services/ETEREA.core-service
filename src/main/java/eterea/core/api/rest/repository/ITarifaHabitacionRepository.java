/**
 * 
 */
package eterea.core.api.rest.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.TarifaHabitacion;

/**
 * @author daniel
 *
 */
@Repository
public interface ITarifaHabitacionRepository extends JpaRepository<TarifaHabitacion, Long> {

	public List<TarifaHabitacion> findAllByBloqueoFacturaAndPrecioOrderByDescripcion(Byte bloqueoFactura,
			BigDecimal precio);

	public List<TarifaHabitacion> findAllByBloqueoFacturaOrderByDescripcion(Byte bloqueoFactura);

}
