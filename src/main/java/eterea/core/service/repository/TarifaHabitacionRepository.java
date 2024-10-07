/**
 * 
 */
package eterea.core.service.repository;

import java.math.BigDecimal;
import java.util.List;

import eterea.core.service.kotlin.model.TarifaHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface TarifaHabitacionRepository extends JpaRepository<TarifaHabitacion, Long> {

	List<TarifaHabitacion> findAllByBloqueoFacturaAndPrecioOrderByDescripcion(Byte bloqueoFactura,
			BigDecimal precio);

	List<TarifaHabitacion> findAllByBloqueoFacturaOrderByDescripcion(Byte bloqueoFactura);

}
