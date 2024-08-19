/**
 * 
 */
package eterea.core.api.rest.repository;

import java.math.BigDecimal;
import java.util.List;

import eterea.core.api.rest.kotlin.model.TarifaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface TarifaHabitacionRepository extends JpaRepository<TarifaHabitacion, Long> {

	public List<TarifaHabitacion> findAllByBloqueoFacturaAndPrecioOrderByDescripcion(Byte bloqueoFactura,
			BigDecimal precio);

	public List<TarifaHabitacion> findAllByBloqueoFacturaOrderByDescripcion(Byte bloqueoFactura);

}
