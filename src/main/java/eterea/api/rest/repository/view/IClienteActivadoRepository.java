/**
 * 
 */
package eterea.api.rest.repository.view;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.api.rest.model.view.ClienteActivado;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteActivadoRepository extends JpaRepository<ClienteActivado, Long> {

}
