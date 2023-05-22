/**
 * 
 */
package eterea.core.api.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eterea.core.api.rest.model.ClienteInternet;

/**
 * @author daniel
 *
 */
@Repository
public interface IClienteInternetRepository extends JpaRepository<ClienteInternet, Long> {

}
