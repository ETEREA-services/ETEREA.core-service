/**
 * 
 */
package eterea.core.service.repository.view;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eterea.core.service.model.view.UsuarioVencimiento;

/**
 * @author daniel
 *
 */
public interface IUsuarioVencimientoRepository extends JpaRepository<UsuarioVencimiento, Long> {

	public List<UsuarioVencimiento> findAllByFechaVencimiento(OffsetDateTime fechaVencimiento);

}
