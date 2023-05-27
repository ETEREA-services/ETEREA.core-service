/**
 * 
 */
package eterea.core.api.rest.repository.view;

import eterea.core.api.rest.kotlin.model.view.ClienteActivado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteActivadoRepository extends JpaRepository<ClienteActivado, Long> {

}
