/**
 * 
 */
package eterea.core.service.repository.view;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.service.model.view.ProductoCantidad;
import eterea.core.service.model.view.pk.ProductoCantidadPk;

/**
 * @author daniel
 *
 */
@Repository
public interface IProductoCantidadRepository extends JpaRepository<ProductoCantidad, ProductoCantidadPk> {

	public List<ProductoCantidad> findAllByFechaServicio(OffsetDateTime fechaServicio);

}
